package com.aitt.service;

import java.util.List;

import com.aitt.entity.Book;

public interface BookService {

	//for getting all records frm book
	public List<Book> getBooks();
	
	//to save obj
	
	public boolean saveBookRecord(Book book);
	
	//to delete rec
	
	public void deleteBookRecord(Integer bookId);
	
	//for update
	
	public Book updateBookById(Integer bookId);
	
}
