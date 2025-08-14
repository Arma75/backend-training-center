package com.example.usu1.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.usu1.dto.TodoItemDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class TodoItemController {
    private static final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @PostMapping("/addTodoItem")
    public String addTodoItem(HttpServletRequest request, @RequestBody TodoItemDto todoItemDto) {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();

        logger.info("[API Call] IP: {}, Time: {}, Todo Content: {}", clientIp, now, todoItemDto.getContent());
        
        return "Todo item '" + todoItemDto.getContent() + "' added successfully!";
    }
}
