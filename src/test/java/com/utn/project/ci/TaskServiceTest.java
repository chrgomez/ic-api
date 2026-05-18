package com.utn.project.ci;

import com.utn.project.ci.dto.TaskRequestDTO;
import com.utn.project.ci.entity.Project;
import com.utn.project.ci.entity.Task;
import com.utn.project.ci.repository.ProjectRepository;
import com.utn.project.ci.repository.TaskRepository;
import com.utn.project.ci.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnAllTasks() {

        Task task = new Task();
        task.setTitle("Tarea Test");

        when(taskRepository.findAll())
                .thenReturn(Arrays.asList(task));

        var result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Tarea Test", result.get(0).getTitle());
    }

    @Test
    void shouldReturnTaskById() {

        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        var result = taskService.getTaskById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void shouldCreateTask() {

        Project project = new Project();
        project.setId(1L);

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Nueva Tarea");
        dto.setDescription("Descripcion");
        dto.setProjectId(1L);

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle(dto.getTitle());
        savedTask.setDescription(dto.getDescription());
        savedTask.setProject(project);

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(savedTask);

        Task result = taskService.createTask(dto);

        assertNotNull(result);
        assertEquals("Nueva Tarea", result.getTitle());
        assertEquals("Descripcion", result.getDescription());
    }

    @Test
    void shouldThrowExceptionWhenProjectNotFound() {

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setProjectId(99L);

        when(projectRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> taskService.createTask(dto)
        );

        assertEquals("Proyecto no encontrado", exception.getMessage());
    }

    @Test
    void shouldDeleteTask() {

        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1))
                .deleteById(1L);
    }
}
