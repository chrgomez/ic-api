package com.utn.project.ci;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ProjectCi {
    
    private final String version = "1.0.0";  
    
    public static void main(String[] args) {
        SpringApplication.run(ProjectCi.class, args);
        
        log.info("¡API Spring Boot Iniciada Correctamente!");
    }
    
}
