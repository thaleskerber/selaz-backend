package com.teste.selaz.selaz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.selaz.selaz.entities.TaskEntity;
import com.teste.selaz.selaz.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<TaskEntity> getAll(){
        return repository.findAll();
    }

    public TaskEntity getById(Long id){
        return repository.findById(id).orElse(null);
    }
}