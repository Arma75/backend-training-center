package com.example.usu1.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usu1.dto.TodoItemDto;
import com.example.usu1.mapper.TodoItemMapper;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemMapper todoItemMapper;

    public List<TodoItemDto> selectList(TodoItemDto todoItemDto) {
        if (todoItemDto.getStartFromDt() != null && todoItemDto.getStartToDt() != null) {
            if (todoItemDto.getStartFromDt().after(todoItemDto.getStartToDt())) {
                throw new IllegalArgumentException("startToDt는 startFromDt보다 이전 날짜일 수 없습니다.");
            }
        }

        int offset = (todoItemDto.getPage() <= 0? 0 : (todoItemDto.getPage() - 1)) * todoItemDto.getSize();
        todoItemDto.setOffset(offset);
        if (todoItemDto.getSort() != null && !todoItemDto.getSort().isEmpty()) {
            List<String> sortList = Arrays.asList(todoItemDto.getSort().split(","))
                                         .stream()
                                         .map(String::trim)
                                         .collect(Collectors.toList());
            todoItemDto.setSortList(sortList);
        }

        List<TodoItemDto> itemList = todoItemMapper.selectList(todoItemDto);

        return itemList;
    }

    public TodoItemDto selectTodoItem(TodoItemDto todoItemDto) {
        return todoItemMapper.selectTodoItem(todoItemDto);
    }

    public int deleteTodoItem(TodoItemDto todoItemDto) {
        return todoItemMapper.deleteTodoItem(todoItemDto);
    }

    public int updateTodoItem(TodoItemDto todoItemDto) {
        return todoItemMapper.updateTodoItem(todoItemDto);
    }

    public int addTodoItem(TodoItemDto todoItemDto) {
        return todoItemMapper.insertTodoItem(todoItemDto);
    }
}
