package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet,Long> {
	
	public Buffet findByNome(String s);
	

}
