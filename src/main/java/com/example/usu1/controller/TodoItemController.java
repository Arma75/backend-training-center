package com.example.usu1.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.usu1.dto.TodoItemDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@CrossOrigin("*")
public class TodoItemController {
    private static final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    private DataSource dataSource;

    @PostMapping("/addTodoItem")
    public String addTodoItem(HttpServletRequest request, @RequestBody TodoItemDto todoItemDto) {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();

        logger.info("[API Call] IP: {}, Time: {}, Todo Content: {}", clientIp, now, todoItemDto.getContent());

        Connection conn;
        PreparedStatement pstmt;

        try {
            String sql = "INSERT INTO todo_item (content) VALUES (?)";
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, todoItemDto.getContent());
            pstmt.executeUpdate();
            pstmt.close();

            conn.close();

            return "Todo item '" + todoItemDto.getContent() + "' added successfully to DB!";
        } catch( SQLException e ) {
            logger.error("DB INSERT failed: {}", e.getMessage());
            e.printStackTrace();
            return "Error adding todo item: " + e.getMessage();
        }
    }
}
