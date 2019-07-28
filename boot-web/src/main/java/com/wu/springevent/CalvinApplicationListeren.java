package com.wu.springevent;


import com.wu.util.PrintlnUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CalvinApplicationListeren implements ApplicationListener<CalvinApplicationEvent> {
    @Override
    public void onApplicationEvent(CalvinApplicationEvent event) {
        Object source = event.getSource();
        System.out.println("---------------------------------------");
        PrintlnUtil.printTack(source.getClass().getName());
    }
}
