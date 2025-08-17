package com.example.usu1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usu1.dto.TodoItemDto;
import com.example.usu1.mapper.TodoItemMapper;

@Repository
public class TodoItemDao {
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
