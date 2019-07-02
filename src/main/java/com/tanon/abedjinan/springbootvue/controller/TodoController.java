package com.tanon.abedjinan.springbootvue.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tanon.abedjinan.springbootvue.model.Todo;
import com.tanon.abedjinan.springbootvue.service.TodoService;
import com.tanon.abedjinan.springbootvue.utils.TodoDTO;


@Controller
@RequestMapping("/todo")
public class TodoController {
	
	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
		
	}
	
	
	@GetMapping("/list") // Map ONLY GET Requests
	public Collection<TodoDTO> listTodo () {
		
		Collection<TodoDTO> todos = todoService.findAll();
		return todos;
	}
	
	@GetMapping("/{id}") // Map ONLY GET Requests
	public ResponseEntity<TodoDTO> getTodo (@PathVariable long id) {
		
		Optional<TodoDTO> todo = todoService.findById(id);
		return todo.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping()
	public ResponseEntity<Void> create(@RequestBody TodoDTO dto) {
		// This returns a JSON or XML with the users
		Optional<Todo> match = todoService.findTodo(dto.getValue());
		if(!match.isPresent()) {
			todoService.create(dto);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		
		 return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PostMapping()
	public ResponseEntity<Void> update(@RequestBody TodoDTO dto) {
		// This returns a JSON or XML with the users
		Optional<Todo> match = todoService.findTodo(dto.getValue());
		if(!match.isPresent() && dto.getValue() != null || match.isPresent() && match.get().getId().equals(dto.getValue()) ) {
			todoService.update(dto);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}


	

}
