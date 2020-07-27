package com.rumenz;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DemoApplication  {
    //1.基于元注解的注解
    @MyAutowired
    private  Rumenz rumenz1;



    @Autowired
    private SuperRumenz superRumenz;


    @InjectRumenz
    private  Rumenz rumenz2;
    //2.AutowiredAnnotationBeanPostProcessor
    @Bean
    public static AutowiredAnnotationBeanPostProcessor injectRumenz(){
        AutowiredAnnotationBeanPostProcessor ab=new AutowiredAnnotationBeanPostProcessor();
        //添加单个自定义注解,多个可以使用setAutowiredAnnotationTypes
        ab.setAutowiredAnnotationType(InjectRumenz.class);
        return ab;
    }


   
    public static void main(String[] args) {


        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader xr=new XmlBeanDefinitionReader(ac);
        xr.loadBeanDefinitions("Beans.xml");
        
        ac.register(DemoApplication.class);
        ac.refresh();
        DemoApplication demoApplication = ac.getBean(DemoApplication.class);


        System.out.println(demoApplication.rumenz1.getName());

        System.out.println(demoApplication.rumenz2.getName());



        System.out.println(demoApplication.superRumenz);


        ac.close();
    }

   


}