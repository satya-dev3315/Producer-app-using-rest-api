package com.aitt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aitt.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

	//to get only active records in console, after soft delete
	
	public List<Book> findByActiveSW(String status);
}
