package com.wu;

import com.wu.entry.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println(user.toString());
    }
}
