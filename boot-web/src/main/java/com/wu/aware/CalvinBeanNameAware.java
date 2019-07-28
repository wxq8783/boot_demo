package com.wu.aware;

import com.wu.util.PrintlnUtil;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class CalvinBeanNameAware implements BeanNameAware , BeanClassLoaderAware {

    private String beanName;

    private ClassLoader classLoader;
    @Override
    public void setBeanName(String name) {
        PrintlnUtil.printTack("beanName is set to "+name);
        this.beanName = "CalvinBeanSet";
    }

    private String getBeanName(){
        return this.beanName;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
