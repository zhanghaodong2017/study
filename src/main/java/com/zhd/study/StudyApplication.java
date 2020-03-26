package com.zhd.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyApplication {

    public static volatile String userame = "abc";
    public static volatile Integer age = 123;


    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

}
