package com.teste.selaz.selaz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.selaz.selaz.dtos.TaskDTO;
import com.teste.selaz.selaz.entities.TaskEntity;
import com.teste.selaz.selaz.services.TaskService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/api/tasks")
@CrossOrigin
public class TaskController {
    
    @Autowired
    private TaskService service;

    @GetMapping
    public List<TaskEntity> get(){
        return service.getAll();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        TaskEntity user = service.getById(id);
        if(user == null) throw new EntityNotFoundException("Task not found");

        service.delete(user);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<TaskEntity> update(@PathVariable("id") Long id, @RequestBody TaskDTO userUpdated) throws Exception{
        TaskEntity taskEntity = service.update(id, userUpdated);

        return ResponseEntity.ok().body(taskEntity);
    }

    @PostMapping
    public ResponseEntity<Void> insert(TaskDTO taskDto) throws Exception{
        service.insert(taskDto);

        return ResponseEntity.ok().build();
    }
}
