package com.wu.service;

public class CalvinRegistryService {

    private String desc = "desc from class";

    public String add(int a , int b){
        return String.valueOf(a+b)+"   "+getDesc();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
