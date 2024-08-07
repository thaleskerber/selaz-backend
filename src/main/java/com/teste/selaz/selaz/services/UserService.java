package com.teste.selaz.selaz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.selaz.selaz.entities.UserEntity;
import com.teste.selaz.selaz.repositories.UserRepository;

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
}