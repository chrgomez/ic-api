package com.utn.project.ci;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.project.ci.controller.TaskController;
import com.utn.project.ci.dto.TaskRequestDTO;
import com.utn.project.ci.entity.Project;
import com.utn.project.ci.entity.Task;
import com.utn.project.ci.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class) // Levanta SOLO el TaskController
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    private Task mockTask;

    @BeforeEach
    void setUp() {
        Project mockProject = new Project();
        mockProject.setId(1L);

        mockTask = new Task();
        mockTask.setId(1L);
        mockTask.setTitle("Tarea Test");
        mockTask.setDescription("Desc Test");
        mockTask.setCompleted(false);
        mockTask.setProject(mockProject);
    }

    @Test
    void debeDevolverTodasLasTareas() throws Exception {
        Mockito.when(taskService.getAllTasks()).thenReturn(Arrays.asList(mockTask));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Tarea Test"));
    }

    @Test
    void debeDevolverTareaPorId() throws Exception {
        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(mockTask));

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Tarea Test"));
    }

    @Test
    void debeDevolver404SiTareaNoExiste() throws Exception {
        Mockito.when(taskService.getTaskById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tasks/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void debeCrearUnaTarea() throws Exception {
        Mockito.when(taskService.createTask(any(TaskRequestDTO.class))).thenReturn(mockTask);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Tarea Test"));
    }

    @Test
    void debeEliminarUnaTarea() throws Exception {
        Mockito.doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());
    }
}