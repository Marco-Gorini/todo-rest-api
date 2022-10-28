package com.soprasteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.soprasteria.model.ToDoElement;
import com.soprasteria.parameters.AddToDoParam;
import com.soprasteria.parameters.UpdateChangedToDoParam;
import com.soprasteria.parameters.UpdateTextToDoParam;
import com.soprasteria.services.ToDoElementService;

import java.util.Optional;

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
	public ResponseEntity<Object> getElementFromId(@PathVariable(value = "idtodo") int idToDo) {
		Optional<ToDoElement> toDoElement = toDoElementService.getToDoFromId(idToDo);
		if(toDoElement.isEmpty()) {
			return new ResponseEntity<Object> (null,HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<Object> (toDoElement.get(),HttpStatus.OK);
		}
	}
	
	@PostMapping("/addtodo")
	public ResponseEntity<Object> addToDo (@RequestBody AddToDoParam atdp){
		return new ResponseEntity<Object> (toDoElementService.createNewToDo(atdp.getText()), HttpStatus.OK);
	}
	
	@DeleteMapping("/todoremove/{idtodo}")
	public void removeToDo (@PathVariable(value = "idtodo") int idToDo){
		toDoElementService.deleteToDo(idToDo);
	}
	
	@PutMapping("/todotoupdatetext") 
	public ResponseEntity<Object> updateToDoText (@RequestBody UpdateTextToDoParam uttdp){
		ToDoElement toDoElement = toDoElementService.updateToDoElementText(uttdp.getIdToDo(), uttdp.getTextModified());
		if(toDoElement == null) {
			return new ResponseEntity<Object> (null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object> (toDoElement, HttpStatus.OK);
	}
	
	@PutMapping("/todotoupdatechanged") 
	public ResponseEntity<Object> updateToDoChanged (@RequestBody UpdateChangedToDoParam uctdp){
		boolean state = uctdp.isChanged();
		if(state == true) {
			state = false;
		}
		else {
			state = true;
		}
		ToDoElement toDoElement = toDoElementService.updateToDoElementCompleted(uctdp.getIdToDo(),state);
		if(toDoElement == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<Object>(toDoElement, HttpStatus.OK);
	}
	
}
