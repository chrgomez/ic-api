package com.utn.project.ci.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}