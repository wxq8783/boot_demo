package com.wu.springevent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class CalvinApplicationPublish implements ApplicationEventPublisherAware , ApplicationContextAware {

    private ApplicationEventPublisher applicationEventPublisher;

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void publishEvent(){
        this.applicationEventPublisher.publishEvent(new CalvinApplicationEvent(this.applicationContext));
    }
}
