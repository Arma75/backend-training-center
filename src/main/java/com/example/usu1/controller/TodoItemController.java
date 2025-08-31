package com.example.usu1.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.usu1.dto.TodoItemDto;
import com.example.usu1.mapper.TodoItemMapper;
import com.example.usu1.service.TodoItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@CrossOrigin("*")
@Tag(name = "Todo list API", description = "투두 아이템 관리")
public class TodoItemController {
    private static final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    private TodoItemService todoItemService;

    @Operation(summary = "투두 아이템 목록 조회", description = "조건에 따라 투두 아이템 목록을 조회합니다.")
    @GetMapping("/selectList")
    public ResponseEntity<?> selectList(
        HttpServletRequest request,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sort,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String content,
        @RequestParam(required = false) Boolean completed,
        @RequestParam(required = false) String startFromDt,
        @RequestParam(required = false) String startToDt
    ) {
        try {
            TodoItemDto todoItemDto = new TodoItemDto();
            todoItemDto.setPage(page);
            todoItemDto.setSize(size);
            todoItemDto.setSort(sort);
            todoItemDto.setTitle(title);
            todoItemDto.setContent(content);
            todoItemDto.setCompleted(completed);
            if( startFromDt != null ) {
                todoItemDto.setStartFromDt(new SimpleDateFormat("yyyyMMdd").parse(startFromDt));
            }
            if( startToDt != null ) {
                todoItemDto.setStartToDt(new SimpleDateFormat("yyyyMMdd").parse(startToDt));
            }

            return new ResponseEntity<>(todoItemService.selectList(todoItemDto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "투두 아이템 상세 조회", description = "seq 값에 따른 투두 아이템 상세 정보를 조회합니다.")
    @GetMapping("/view")
    public ResponseEntity<TodoItemDto> selectTodoItem(HttpServletRequest request, @RequestParam Long seq) {
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setSeq(seq);
        
        return new ResponseEntity<>(todoItemService.selectTodoItem(todoItemDto), HttpStatus.OK);
    }

    @Operation(summary = "투두 아이템 삭제", description = "투두 아이템 정보들을 삭제합니다.")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteTodoItem(HttpServletRequest request, @RequestBody TodoItemDto todoItemDto) {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();

        logger.info("[API Call] IP: {}, Time: {}", clientIp, now);
        logger.info("todoItemDto.getSeqList(): {}", todoItemDto.getSeqList());
        
        // 필수값 검사
        if( todoItemDto.getSeq() == null && (todoItemDto.getSeqList() == null || todoItemDto.getSeqList().isEmpty()) ) {
            return new ResponseEntity<>("아이템 ID는 필수 입력 항목입니다.", HttpStatus.BAD_REQUEST);
        }

        int deleteCount = todoItemService.deleteTodoItem(todoItemDto);
        logger.info("deleteCount: {}", deleteCount);

        if( deleteCount > 0 ) {
            return new ResponseEntity<String>("삭제를 완료했습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "투두 아이템 추가", description = "투두 아이템 정보를 신규 추가합니다.")
    @PostMapping("/insert")
    public ResponseEntity<String> addTodoItem(HttpServletRequest request, @RequestBody TodoItemDto todoItemDto) {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();

        logger.info("[API Call] IP: {}, Time: {}", clientIp, now);
        
        // 필수값 검사
        if( todoItemDto.getTitle() == null || todoItemDto.getTitle().trim().isEmpty() ) {
            return new ResponseEntity<>("제목은 필수 입력 항목입니다.", HttpStatus.BAD_REQUEST);
        }

        // 길이 검사
        if( todoItemDto.getTitle().length() > 100 ) {
            return new ResponseEntity<>("제목은 100자를 초과할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
        if( todoItemDto.getContent() != null && todoItemDto.getContent().length() > 1000 ) {
            return new ResponseEntity<>("내용은 1000자를 초과할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        int addCount = todoItemService.addTodoItem(todoItemDto);
        logger.info("addCount: {}", addCount);

        if( addCount > 0 ) {
            return new ResponseEntity<String>("추가를 완료했습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "투두 아이템 수정", description = "투두 아이템 정보들을 수정합니다.")
    @PostMapping("/update")
    public ResponseEntity<String> updateTodoItem(HttpServletRequest request, @RequestBody List<TodoItemDto> todoItemDtoList) {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();

        logger.info("[API Call] IP: {}, Time: {}", clientIp, now);

        // 필수값 검사
        if( todoItemDtoList == null || todoItemDtoList.isEmpty() ) {
            return new ResponseEntity<>("업데이트할 아이템 목록이 비어 있습니다.", HttpStatus.BAD_REQUEST);
        }

        for( TodoItemDto todoItemDto : todoItemDtoList ) {
            // 필수값 검사
            if( todoItemDto.getSeq() == null ) {
                return new ResponseEntity<>("Seq 값은 필수입니다.", HttpStatus.BAD_REQUEST);
            }
            if( todoItemDto.getTitle() == null && todoItemDto.getTitle().trim().isEmpty() ) {
                return new ResponseEntity<>("제목은 필수 입력 항목입니다.", HttpStatus.BAD_REQUEST);
            }

            // 길이 검사
            if( todoItemDto.getTitle().length() > 100 ) {
                return new ResponseEntity<>("제목은 100자를 초과할 수 없습니다.", HttpStatus.BAD_REQUEST);
            }
            if( todoItemDto.getContent() != null && todoItemDto.getContent().length() > 1000 ) {
                return new ResponseEntity<>("내용은 1000자를 초과할 수 없습니다.", HttpStatus.BAD_REQUEST);
            }
        }

        int updateCount = todoItemService.updateTodoItem(todoItemDtoList);
        logger.info("updateCount: {}", updateCount);

        if( updateCount > 0 ) {
            return new ResponseEntity<String>("수정을 완료했습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
