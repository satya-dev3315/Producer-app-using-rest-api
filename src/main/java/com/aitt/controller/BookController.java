package com.aitt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aitt.entity.Book;
import com.aitt.repo.BookRepo;
import com.aitt.service.BookServiceImpl;

@Controller
public class BookController {

	@Autowired
	private BookServiceImpl bookServiceImpl;

	//method to display a form
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("book", new Book()); // book empty obj we need to send for form filling."book" key we will bind in
											// index.html for form binding
		return mav;
	}
	
	//save obj 
	 
	@PostMapping("/book")
	public ModelAndView saveBook(Book book){  // frm above form, the form will be filled and will return the book obj,so take book as parameter
		ModelAndView mav = new ModelAndView("index"); // after inserting a rec , return to same page
     boolean status = bookServiceImpl.saveBookRecord(book);
     if(status) {
    	 mav.addObject("successMsg", "Book Saved");
     }else {
    	 mav.addObject("errorMsg", "Book not Saved");
     }
     
     return mav;
		
	}

	// getting all rec
	
	@GetMapping("/bookOperations")
	public ModelAndView getBookRecords() {
		ModelAndView mav = new ModelAndView("list-books");// this is viewname
		List<Book> listBooks = bookServiceImpl.getBooks();
		mav.addObject("books", listBooks); // this "books" is key n should match in for() in html;ie: <tr th:each="b :
											// ${books}">
		// by using key in html we r gettin data n we r showin it on console as
		// modelAndView obj
		return mav;

	}
	
	//delete a record
	
	@GetMapping("/delete")
	public ModelAndView deleteBookRec(@RequestParam("bookId") Integer bookId) {
		bookServiceImpl.deleteBookRecord(bookId);
		//return back to this page once record is deleted
		ModelAndView mav = new ModelAndView();
		List<Book> listBooks = bookServiceImpl.getBooks();
		mav.addObject("books", listBooks);
		mav.setViewName("list-books");
		return mav;	
	}
	
	//update rec
	
	@GetMapping("/edit")
	public ModelAndView editBookRec(@RequestParam("bookId") Integer bookId) {
		Book bookObject = bookServiceImpl.updateBookById(bookId);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("book", bookObject); //book is key , should b same as form binding key at line 21 index.html.Also here data will nt b empty, above index() was empty 
		return mav;
	}
	
}
