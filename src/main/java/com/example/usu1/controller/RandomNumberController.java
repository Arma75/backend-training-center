package com.example.usu1.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ResponseBody
public class RandomNumberController {
    @GetMapping("random")
    public int getRandomNumber() {
        return new Random().nextInt(10);
    }
    
}
