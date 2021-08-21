package com.TestDocker.BooksDocker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TestDocker.BooksDocker.Models.Book;
import com.TestDocker.BooksDocker.Repository.BookRepository;

@RestController
public class MainController {
	@Autowired
	public BookRepository bookRepository;

	@GetMapping("/test")
	public String test() {
		return new String("Working from DOcker Bopoks proj ");
	}
	@GetMapping("/")
	public List<Book> fetchAllBooks() {
		List<Book> books;
		try {
			books = bookRepository.findAll();

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured in fetchAllBooks", ex);

		}
		return books;
	}

	@GetMapping("/{bookID}")
	public Book fetchBookfromID(@PathVariable("bookID") Long bookID) {
		Book book;
		try {
			book = bookRepository.getById(bookID);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured in fetchBookfromID", ex);

		}
		return book;
	}

	@GetMapping("/search/{title}")
	public List<Book> searchBookByTitle(@PathVariable("title") String title) {
		List<Book> books;
		try {
			//System.out.println(title);
			books = bookRepository.fuzzySearchByTitle(title);
			System.out.println(books);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured in searchBookByTitle", ex);
		}
		return books;
	}

	@PostMapping("/insertBooks")
	public String insertBooks(@RequestBody List<Book> books) {

		for (Book b : books) {
			System.out.println(b.toString());
			Book b1 = bookRepository.save(b);
			if (b1 == null)
				return "Book object is null";
		}
		return null;
	}
}
