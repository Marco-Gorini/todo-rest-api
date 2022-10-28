package com.soprasteria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.model.ToDoElement;
import com.soprasteria.repositories.ToDoElementRepository;

@Service
public class ToDoElementService {
	
	@Autowired
	private ToDoElementRepository toDoElementRepository;
	
	//METHODS
	
	
	/*
	 * Will be called by the controller, and will return all the todo elements in the database
	 */
	public List<ToDoElement> getAllToDoElements(){
		return toDoElementRepository.findAll();
	}
	
	/*
	 * Will return a ToDo from the id when called by the controller
	 */
	public Optional<ToDoElement> getToDoFromId(int idToDo) {
		return toDoElementRepository.findById(idToDo);
	}
	
	/*
	 * Will return the new ToDoElement to create when called from the controller
	 */
	public ToDoElement createNewToDo(String textToDo) {
		ToDoElement newToDo = new ToDoElement();
		newToDo.setCompleted(false);
		newToDo.setElement(textToDo);
		return toDoElementRepository.save(newToDo);
	}
	
	/*
	 * Will delete a ToDo from its Id when called from the controller
	 */
	public void deleteToDo(int idToDo) {
		toDoElementRepository.deleteById(idToDo);
	}
	
	/*
	 * Will update the text of the ToDo when called by the controller
	 */
	public ToDoElement updateToDoElementText(int idToDo,String newText) {
		Optional<ToDoElement> toDoToUpdate = toDoElementRepository.findById(idToDo);
		if(toDoToUpdate.isEmpty()) {
			return null;
		}
		toDoToUpdate.get().setElement(newText);
		return toDoToUpdate.get();
	}
	
	/*
	 * Will update the completed boolean of the ToDo when called by the controller
	 */
	public ToDoElement updateToDoElementCompleted(int idToDo, boolean changed) {
		Optional<ToDoElement> toDoToUpdate = toDoElementRepository.findById(idToDo);
		if(toDoToUpdate.isEmpty()) {
			return null;
		}
		toDoToUpdate.get().setCompleted(changed);
		toDoElementRepository.save(toDoToUpdate.get());
		return toDoToUpdate.get();
	}
}
