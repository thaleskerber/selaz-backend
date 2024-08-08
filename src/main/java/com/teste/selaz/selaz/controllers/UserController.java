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

import com.teste.selaz.selaz.entities.UserEntity;
import com.teste.selaz.selaz.services.UserService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public List<UserEntity> get(){
        return service.getAll();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        UserEntity user = service.getById(id);
        if(user == null) throw new EntityNotFoundException("User not found");

        service.delete(user);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable("id") Long id, @RequestBody UserEntity userUpdated) throws Exception{
        UserEntity user = service.update(id, userUpdated);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> insert(UserEntity user) throws Exception{
        service.insert(user);

        return ResponseEntity.ok().build();
    }
}
