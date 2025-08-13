package com.example.usu1.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ResponseBody
public class RandomNumberController {
    private static final Logger logger = LoggerFactory.getLogger(RandomNumberController.class);

    @GetMapping("random")
    public int getRandomNumber(HttpServletRequest request, @RequestParam(defaultValue = "0", required = false) int min, @RequestParam(defaultValue = "10", required = false) int max) {
        String clientIp = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();
        int returnValue = new Random().nextInt(max - min + 1) + min;
        logger.info("[API Call] IP: {}, Time: {}, Returned Value: {}", clientIp, now, returnValue);

        return returnValue;
    }
    
}
