package com.utn.project.ci.service;

import com.utn.project.ci.entity.Task;
import com.utn.project.ci.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. Obtener todas las tareas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // 2. Obtener una tarea por ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // 3. Crear una nueva tarea
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // 4. Eliminar una tarea
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}