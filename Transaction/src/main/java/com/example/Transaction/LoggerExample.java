package com.example.Transaction;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoggerExample {


    @RequestMapping("/")
    public String home() {
        LoggerFactory
        return "hello jihun";
    }
}
