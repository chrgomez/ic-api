package com.utn.project.ci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectCi {
    
    private String version = "1.0.0";
    
    public static void main(String[] args) {
        SpringApplication.run(ProjectCi.class, args);
        System.out.println("¡API Spring Boot Iniciada Correctamente!");
    }
}
