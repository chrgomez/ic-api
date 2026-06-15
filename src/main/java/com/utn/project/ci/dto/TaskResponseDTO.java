package com.utn.project.ci.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskResponseDTO {

    private Long id;
    @NotBlank(message = "El título es obligatorio")
    private String title;
    
    private String description;
    
    @NotNull(message = "El ID del proyecto es obligatorio")
    private Long projectId; // Fíjate que es solo un número, ¡mucho más fácil
}
