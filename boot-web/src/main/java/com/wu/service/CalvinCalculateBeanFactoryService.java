package com.wu.service;

import org.springframework.stereotype.Component;

@Component("calvinCalculate")
public class CalvinCalculateBeanFactoryService  {

    private String desc = " calculate desc";

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String add(int a ,int b){
        return (a+b) + getDesc();
    }
}
