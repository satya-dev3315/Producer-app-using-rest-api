package com.aitt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitt.entity.Book;
import com.aitt.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo repo;

	@Override
	public List<Book> getBooks() {
		//return repo.findAll();
		//for soft del dont get all records but only active rec
		return repo.findByActiveSW("Y");
	}

	@Override
	public boolean saveBookRecord(Book book) {
		//for soft delete, set status of active switch to yes by deafult
		book.setActiveSW("Y");		
		
		Book save = repo.save(book);
		if (save.getBookId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteBookRecord(Integer bookId) {
		//repo.deleteById(bookId);
		
		//for soft delete
		Optional<Book> findById = repo.findById(bookId);
		if(findById.isPresent()) {
			Book book = findById.get();
			book.setActiveSW("N");  //whenever we delete by getting id, change active switch to know. we can check in db for the result.
		    repo.save(book);
		}
	}

	@Override
	public Book updateBookById(Integer bookId) {
		Optional<Book> findById = repo.findById(bookId);
		if(findById.isPresent()) {
			return findById.get();
			
		}
		return null;
	}

}
