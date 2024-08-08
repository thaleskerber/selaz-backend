package com.teste.selaz.selaz.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.selaz.selaz.dtos.TaskDTO;
import com.teste.selaz.selaz.entities.TaskEntity;
import com.teste.selaz.selaz.entities.UserEntity;
import com.teste.selaz.selaz.repositories.TaskRepository;
import com.teste.selaz.selaz.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskEntity> getAll(){
        return repository.findAll();
    }

    public TaskEntity getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public TaskEntity insert(TaskDTO taskDto) throws Exception{
        UserEntity user = userRepository.findById(taskDto.getId()).orElse(null);
        if(user == null) throw new EntityNotFoundException("User not found");

        if(repository.existsByTitle(taskDto.getTitle())) throw new Exception("Task with this title already registered");

        TaskEntity task = new TaskEntity();
        task.setCreatedAt(LocalDateTime.now());
        task.setDueDate(taskDto.getDueDate());
        task.setDescription(taskDto.getDescription());
        task.setTitle(taskDto.getTitle());
        task.setStatus(0);
        task.setUser(user);

        return repository.save(task);
    }

    public TaskEntity update(Long id, TaskDTO taskDto) throws Exception{
        TaskEntity task = this.getById(id);
        if(task == null) throw new EntityNotFoundException("Task not found");

        if(task.getUser().getId() != taskDto.getIdUser()){
            UserEntity user = userRepository.findById(id).orElse(null);
            if(user == null) throw new EntityNotFoundException("User not found");

            task.setUser(user);
        }

        if(!task.getTitle().equalsIgnoreCase(taskDto.getTitle()) && repository.existsByTitle(taskDto.getTitle())) throw new Exception("Task with this title already registered");

        task.setDueDate(taskDto.getDueDate());
        task.setDescription(taskDto.getDescription());
        task.setTitle(taskDto.getTitle());
        task.setStatus(taskDto.getStatus());

        return repository.save(task);
    }

    public void delete(TaskEntity task){
        repository.delete(task);
    }
}