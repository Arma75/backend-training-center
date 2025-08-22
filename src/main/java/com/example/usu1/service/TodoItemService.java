package com.example.usu1.service;

import java.util.Date;
import java.util.List;

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

    public List<TodoItemDto> selectList() {

        List<TodoItemDto> itemList = todoItemMapper.selectList();

        return itemList;
    }

    public int deleteTodoItem(TodoItemDto todoItemDto) {
        return todoItemMapper.deleteTodoItem(todoItemDto);
    }

    public String addTodoItem(@RequestBody TodoItemDto todoItemDto) {
        if( todoItemDto.getStartDt() == null ) {
            todoItemDto.setStartDt(new Date());
        }

        todoItemMapper.insertTodoItem(todoItemDto);

        return "OK";
    }
}
