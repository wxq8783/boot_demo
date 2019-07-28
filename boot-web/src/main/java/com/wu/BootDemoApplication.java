package com.wu;

import com.wu.service.CalvinCalculateBeanFactoryService;
import com.wu.service.CalvinRegistryService;
import com.wu.springevent.CalvinApplicationPublish;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class BootDemoApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootDemoApplication.class, args);
        CalvinRegistryService service = (CalvinRegistryService)applicationContext.getBean("calvinRegistryService");
        String add = service.add(1, 5);
        System.out.println("------>"+add);

//        CalvinCalculateBeanFactoryService beanFactoryService = (CalvinCalculateBeanFactoryService) applicationContext.getBean("calvinCalculate");
//        String s = beanFactoryService.add(1, 3);
//        System.out.println("---->"+s);


//        CalvinApplicationPublish applicationPublish = applicationContext.getBean(CalvinApplicationPublish.class);
//        applicationPublish.publishEvent();
//        String[] names = applicationContext.getBeanDefinitionNames();
//        for(String name : names){
//            System.out.println("-------------------------------------");
//            System.out.println(name);
//        }
    }
}
