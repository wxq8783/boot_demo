package com.wu.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestCondition {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println(listService.showListCmd());
    }
}
