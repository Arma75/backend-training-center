package com.example.usu1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usu1.dto.TodoItemDto;
import com.example.usu1.mapper.TodoItemMapper;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemMapper todoItemMapper;

    public String addTodoItem(@RequestBody TodoItemDto todoItemDto) {
        todoItemMapper.insertTodoItem(todoItemDto);
        return "OK";
    }
}
