package com.wu.entry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class A1 {
//    public A1(B1 b){
//
//    }
    @Autowired
    B1 b;
}
