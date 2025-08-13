package com.example.usu1.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ResponseBody
public class RandomNumberController {
    private static final Logger logger = LoggerFactory.getLogger(RandomNumberController.class);

    @GetMapping("random")
    public int getRandomNumber(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();
        int returnValue = new Random().nextInt(11);
        logger.info("[API Call] IP: {}, Time: {}, Returned Value: {}", clientIp, now, returnValue);

        return returnValue;
    }
    
}
