package com.example.usu1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usu1.dao.TodoItemDao;
import com.example.usu1.dto.TodoItemDto;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemDao todoItemDao;

    public String addTodoItem(@RequestBody TodoItemDto todoItemDto) {
        return todoItemDao.addTodoItem(todoItemDto);
    }
}
