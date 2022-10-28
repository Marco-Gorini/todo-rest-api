package com.soprasteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.model.ToDoElement;
import com.soprasteria.parameters.UpdateChangedToDoParam;
import com.soprasteria.parameters.UpdateTextToDoParam;
import com.soprasteria.services.ToDoElementService;

@CrossOrigin
@RestController
@RequestMapping("/todo")
public class ToDoElementController {
	
	@Autowired
	ToDoElementService toDoElementService;
	
	@GetMapping("")
	public List<ToDoElement> getAllToDo(){
		return toDoElementService.getAllToDoElements();
	}
	
	@GetMapping("/todoelement/{idtodo}")
	public Optional<ToDoElement> getElementFromId(@PathVariable(value = "idtodo") int idToDo) {
		return toDoElementService.getToDoFromId(idToDo);
	}
	
	@PostMapping("/addtodo/{texttodo}")
	public List<ToDoElement> addToDo (@PathVariable(value = "texttodo") String textToDo){
		toDoElementService.createNewToDo(textToDo);
		return toDoElementService.getAllToDoElements();
	}
	
	@DeleteMapping("/todoremove/{idtodo}")
	public List<ToDoElement> removeToDo (@PathVariable(value = "idtodo") int idToDo){
		toDoElementService.deleteToDo(idToDo);
		return toDoElementService.getAllToDoElements();
	}
	
	@PutMapping("/todotoupdatetext") 
	public List<ToDoElement> updateToDoText (@RequestBody UpdateTextToDoParam uttdp){
		toDoElementService.updateToDoElementText(uttdp.getIdToDo(), uttdp.getTextModified());
		return toDoElementService.getAllToDoElements();
	}
	
	@PutMapping("/todotoupdatechanged") 
	public List<ToDoElement> updateToDoChanged (@RequestBody UpdateChangedToDoParam uctdp){
		boolean state = uctdp.isChanged();
		if(state == true) {
			state = false;
		}
		else {
			state = true;
		}
		toDoElementService.updateToDoElementCompleted(uctdp.getIdToDo(),state);
		return toDoElementService.getAllToDoElements();
	}
	
}
