package com.wu.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * 定义一个事件
 */
public class CalvinApplicationEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CalvinApplicationEvent(Object source) {
        super(source);
    }
}
