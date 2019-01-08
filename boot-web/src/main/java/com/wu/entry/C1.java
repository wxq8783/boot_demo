package com.wu.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class C1 {
//    public C1(A1 a){}
    @Autowired
    A1 a;
}
