package com.TestDocker.BooksDocker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TestDocker.BooksDocker.Models.Book;
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title% ")
List<Book> fuzzySearchByTitle(String title);

}
