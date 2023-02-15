package com.simcardapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Author : Yashwanth Thota
 * Email : 17yashwanth29@gmail.com
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class,args);
        }catch (Exception e){
            System.out.println("Error while running the application : " + e.getMessage());
        }
    }
}
