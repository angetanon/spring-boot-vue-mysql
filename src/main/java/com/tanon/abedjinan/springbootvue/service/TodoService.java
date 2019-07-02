package com.tanon.abedjinan.springbootvue.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tanon.abedjinan.springbootvue.mapper.TodoMapper;
import com.tanon.abedjinan.springbootvue.model.Todo;
import com.tanon.abedjinan.springbootvue.utils.TodoDTO;


@Component
public class TodoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
	
	private final TodoRepository todoRepo;
	
	
	private final TodoMapper todoMapper;
	
	public TodoService(TodoRepository todoReository, TodoMapper todoMapper) {
		this.todoRepo = todoReository;
		this.todoMapper = todoMapper;
		
		
	}

	public Optional<Todo> findTodo(Long id) {
		return todoRepo.findById(id);		
	}
	public Collection<TodoDTO> findAll() {
		List<Todo> todos =  todoRepo.findAll();
		List<TodoDTO> dtos = new ArrayList<TodoDTO>();
		todos.forEach(a->{
			TodoDTO dto = todoMapper.toDTO(a);
			dtos.add(dto);
		});
		return dtos;
		//return StreamSupport.stream(todoRepo.findAll().spliterator(), false).map(TodoMapper::toDTO).collect(Collectors.toList());
		
	}
	
	@Transactional
	public void create(TodoDTO dto) {
		Todo todo = new Todo();
		todo.setTitle(dto.getTitle());
		todoRepo.save(todo);
	}

	@Transactional
	public void update(TodoDTO dto) {
		Todo todo = todoRepo.findById(dto.getValue()).get();
		todo.setTitle(dto.getTitle());
	}
	public Optional<TodoDTO> findById(long id) {
		
		return todoRepo.findById(id).map(todoMapper::toDTO);
	}
	
	
	
	
	
}
