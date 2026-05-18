package com.utn.project.ci;

import com.utn.project.ci.dto.ProjectRequestDTO;
import com.utn.project.ci.entity.Project;
import com.utn.project.ci.repository.ProjectRepository;
import com.utn.project.ci.service.ProjectService;
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
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void shouldReturnAllProjects() {

        Project project = new Project();
        project.setName("Proyecto Test");

        when(projectRepository.findAll())
                .thenReturn(Arrays.asList(project));

        var result = projectService.getAllProjects();

        assertEquals(1, result.size());
        assertEquals("Proyecto Test", result.get(0).getName());
    }

    @Test
    void shouldReturnProjectById() {

        Project project = new Project();
        project.setId(1L);

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        var result = projectService.getProjectById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void shouldCreateProject() {

        ProjectRequestDTO dto = new ProjectRequestDTO();
        dto.setName("Nuevo Proyecto");
        dto.setDescription("Descripcion");

        Project savedProject = new Project();
        savedProject.setId(1L);
        savedProject.setName(dto.getName());

        when(projectRepository.save(any(Project.class)))
                .thenReturn(savedProject);

        Project result = projectService.createProject(dto);

        assertNotNull(result);
        assertEquals("Nuevo Proyecto", result.getName());
    }

    @Test
    void shouldDeleteProject() {

        doNothing().when(projectRepository).deleteById(1L);

        projectService.deleteProject(1L);

        verify(projectRepository, times(1))
                .deleteById(1L);
    }
}