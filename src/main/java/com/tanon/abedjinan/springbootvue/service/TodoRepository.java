package com.tanon.abedjinan.springbootvue.service;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tanon.abedjinan.springbootvue.model.Todo;


interface TodoRepository extends JpaRepository<Todo, Long>{
	
//	public List<Todo> findAll();
//	public Optional<Todo> findById(long id);

}
