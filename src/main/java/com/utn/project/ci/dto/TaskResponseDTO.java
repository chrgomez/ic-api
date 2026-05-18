package com.utn.project.ci.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
}
