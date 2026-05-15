package com.utn.project.ci.repository;

import com.utn.project.ci.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Spring Data JPA crea la consulta SQL automáticamente basándose en el nombre del método
    List<Task> findByProjectId(Long projectId);
    
}