package com.wu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WebController {

    @RequestMapping("/demo/info")
    public String getInfo(){
        return "hello word, wuxiaoqing";
    }
}
