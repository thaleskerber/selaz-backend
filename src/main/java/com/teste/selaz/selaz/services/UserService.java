package com.teste.selaz.selaz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.selaz.selaz.entities.UserEntity;
import com.teste.selaz.selaz.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserEntity> getAll(){
        return repository.findAll();
    }

    public UserEntity getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public UserEntity insert(UserEntity userEntity) throws Exception{
        if(repository.existsByUsername(userEntity.getUsername())) throw new Exception("User with this name already registered");

        return repository.save(userEntity);
    }

    public UserEntity update(Long id, UserEntity userEntity) throws Exception{
        UserEntity user = this.getById(id);
        if(user == null) throw new EntityNotFoundException("User not found");

        if(!user.getUsername().equalsIgnoreCase(userEntity.getUsername()) && repository.existsByUsername(userEntity.getUsername())) throw new Exception("User with this name already registered");

        user.setNivel(userEntity.getNivel());
        user.setUsername(userEntity.getUsername());

        return repository.save(user);
    }

    public void delete(UserEntity user){
        repository.delete(user);
    }
}