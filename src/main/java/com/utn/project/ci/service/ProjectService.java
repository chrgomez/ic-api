package com.utn.project.ci.service;

import com.utn.project.ci.dto.ProjectRequestDTO;
import com.utn.project.ci.entity.Project;
import com.utn.project.ci.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    // Final asegura que la dependencia no cambie. Lombok crea el constructor por nosotros.
    private final ProjectRepository projectRepository;
    
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // 1. Obtener todos los proyectos
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // 2. Obtener un proyecto por ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // 3. Crear un nuevo proyecto
    public Project createProject(ProjectRequestDTO dto) {

        Project project = new Project();

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());

        return projectRepository.save(project);
    }

    // 4. Eliminar un proyecto
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}