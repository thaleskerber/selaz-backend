package com.teste.selaz.selaz.repositories;

import com.teste.selaz.selaz.entities.TaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    public boolean existsByTitle(String title);

}