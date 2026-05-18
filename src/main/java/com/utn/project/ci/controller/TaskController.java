package com.utn.project.ci.controller;

import com.utn.project.ci.dto.TaskRequestDTO;
import com.utn.project.ci.dto.TaskResponseDTO;
import com.utn.project.ci.entity.Task;
import com.utn.project.ci.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks") // Misma estructura que /api/projects
public class TaskController {

    private final TaskService taskService;

    // CONSTRUCTOR MANUAL
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET: http://localhost:8080/api/tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // GET: http://localhost:8080/api/tasks/1
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: http://localhost:8080/api/tasks
    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(
            @Valid @RequestBody TaskRequestDTO request) {

        Task createdTask = taskService.createTask(request);

        TaskResponseDTO response = TaskResponseDTO.builder()
                .id(createdTask.getId())
                .title(createdTask.getTitle())
                .description(createdTask.getDescription())
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // DELETE: http://localhost:8080/api/tasks/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
