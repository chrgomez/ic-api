package com.utn.project.ci.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {

    @NotBlank
    private String title;
    private String description;
    private Long projectId;
}