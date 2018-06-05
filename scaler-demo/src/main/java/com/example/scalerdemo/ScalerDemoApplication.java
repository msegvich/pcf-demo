package com.example.scalerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class ScalerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScalerDemoApplication.class, args);
    }

    @RestController
    class SalutationsController{
        @GetMapping("/hello")
        public String hello(){
            return "Greetings and Salutations";
        }
    }

    @RestController
    class ScalingController{
        @GetMapping("/scale-me")
        public String slow(){
            try{
                sleep(4000);
            }
            finally {
                return "Need more power!";
            }
        }
    }
}
