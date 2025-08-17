package com.example.usu1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usu1.dto.TodoItemDto;
import com.example.usu1.mapper.TodoItemMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemMapper todoItemMapper;

    public String addTodoItem(@RequestBody TodoItemDto todoItemDto) {
        try {
            todoItemMapper.insertTodoItem(todoItemDto);
            return "Todo item '" + todoItemDto.getContent() + "' added successfully to DB!";
        } catch( Exception e ) {
            e.printStackTrace();
            return "Error adding todo item: " + e.getMessage();
        }
    }
}
