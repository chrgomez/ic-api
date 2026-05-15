package com.utn.project.ci.controller;

import com.utn.project.ci.entity.Project;
import com.utn.project.ci.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects") // Esta será la URL base
public class ProjectController {

    private final ProjectService projectService;
    
    // CONSTRUCTOR MANUAL: Spring inyecta el servicio automáticamente aquí
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // GET: http://localhost:8080/api/projects
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    // GET: http://localhost:8080/api/projects/1
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: http://localhost:8080/api/projects
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        // Retornamos un status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // DELETE: http://localhost:8080/api/projects/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}