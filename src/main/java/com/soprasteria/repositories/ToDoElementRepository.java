package com.soprasteria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.model.ToDoElement;

public interface ToDoElementRepository extends JpaRepository<ToDoElement,Integer>{

}
