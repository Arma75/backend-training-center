package com.example.usu1.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.usu1.dto.TodoItemDto;
import com.example.usu1.mapper.TodoItemMapper;
import com.example.usu1.service.TodoItemService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@CrossOrigin("*")
public class TodoItemController {
    private static final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    private TodoItemService todoItemService;

    @PostMapping("/addTodoItem")
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

        return new ResponseEntity<String>(todoItemService.addTodoItem(todoItemDto), HttpStatus.OK);
    }
}
