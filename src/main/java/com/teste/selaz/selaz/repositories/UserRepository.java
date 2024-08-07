package com.teste.selaz.selaz.repositories;

import com.teste.selaz.selaz.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}