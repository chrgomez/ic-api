package com.utn.project.ci.repository;

import com.utn.project.ci.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Aquí luego podemos agregar búsquedas personalizadas, ej: buscar por completadas
}