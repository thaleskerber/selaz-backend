package com.teste.selaz.selaz.dtos;

import java.time.LocalDateTime;

public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private Integer status;

    private Long idUser;

    private LocalDateTime dueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public Integer getStatus(){
        return status;
    }
    
    public void setStatus(Integer status){
        this.status = status;
    }

    public Long getIdUser(){
        return idUser;
    }

    public void setIdUser(Long idUser){
        this.idUser = idUser;
    }

    public LocalDateTime getDueDate(){
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate){
        this.dueDate = dueDate;
    }
}
