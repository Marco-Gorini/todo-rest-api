package com.soprasteria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ToDo")
public class ToDoElement {
	
	//ATTRIBUTES
	
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Self-increase
	@JoinColumn(name = "id_to_do") //Match the column in the database
	private int idToDo;
	
	@NotNull(message = "This value can't be null.")
	private String element;
	
	@NotNull(message = "This value can't be null.")
	private Boolean completed;
	
	//GETTERS AND SETTERS

	public int getIdToDo() {
		return idToDo;
	}

	public void setIdToDo(int idToDo) {
		this.idToDo = idToDo;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	
}
