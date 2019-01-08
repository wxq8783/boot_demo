package com.wu.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class B1 {
//    public B1(C1 c){}
    @Autowired
    C1 c;
}
