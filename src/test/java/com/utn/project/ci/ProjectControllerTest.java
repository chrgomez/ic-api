package com.utn.project.ci;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.project.ci.controller.ProjectController;
import com.utn.project.ci.dto.ProjectRequestDTO;
import com.utn.project.ci.entity.Project;
import com.utn.project.ci.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class) // Levanta SOLO el ProjectController
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simula peticiones HTTP (Postman interno)

    @Autowired
    private ObjectMapper objectMapper; // Convierte objetos Java a JSON

    @MockBean
    private ProjectService projectService; // Simulamos el servicio (No toca la BD real)

    private Project mockProject;

    @BeforeEach
    void setUp() {
        // Preparamos un proyecto falso antes de cada test
        mockProject = new Project();
        mockProject.setId(1L);
        mockProject.setName("Proyecto Test");
        mockProject.setDescription("Descripción Test");
        mockProject.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void debeDevolverTodosLosProyectos() throws Exception {
        Mockito.when(projectService.getAllProjects()).thenReturn(Arrays.asList(mockProject));

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Proyecto Test"));
    }

    @Test
    void debeDevolverProyectoPorId() throws Exception {
        Mockito.when(projectService.getProjectById(1L)).thenReturn(Optional.of(mockProject));

        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Proyecto Test"));
    }

    @Test
    void debeDevolver404SiProyectoNoExiste() throws Exception {
        Mockito.when(projectService.getProjectById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/projects/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void debeCrearUnProyecto() throws Exception {
        Mockito.when(projectService.createProject(any(ProjectRequestDTO.class))).thenReturn(mockProject);

        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockProject)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Proyecto Test"));
    }

    @Test
    void debeEliminarUnProyecto() throws Exception {
        Mockito.doNothing().when(projectService).deleteProject(1L);

        mockMvc.perform(delete("/api/projects/1"))
                .andExpect(status().isNoContent());
    }
}