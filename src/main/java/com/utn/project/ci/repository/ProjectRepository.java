package com.utn.project.ci.repository;

import com.utn.project.ci.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Si más adelante queremos buscar proyectos por nombre, lo declaramos aquí:
    // Optional<Project> findByName(String name);
}