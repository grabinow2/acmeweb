package com.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Standard main class for Spring, generated by Spring template
 */

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Application {

    private static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = new AnnotationConfigApplicationContext(Application.class);
        }
        return applicationContext;
    }
}
