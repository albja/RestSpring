package com.questRestSpring.controller;

import com.questRestSpring.model.Book;
import com.questRestSpring.repository.BookRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  @Autowired
  BookRepository bookRepository;

  @GetMapping("/books")
  public List<Book> index() {
    return bookRepository.findAll();
  }

  @GetMapping("/books/{id}")
  public Book show(@PathVariable int id) {
    Book book = new Book();

    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isPresent()) {
      book = bookOptional.get();
    }
    return book;
  }

  @PostMapping("/books/search")
  public List<Book> search(@RequestBody Map<String, String> body) {
    String searchTerm = body.get("text");
    return bookRepository.findByTitleContainingOrAuthorContaining(
      searchTerm,
      searchTerm
    );
  }

  @PostMapping("/books")
  public Book create(@RequestBody Book book) {
    return bookRepository.save(book);
  }

  @PutMapping("/books/{id}")
  public Book update(@PathVariable int id, @RequestBody Book book) {
    // getting book
    Book bookToUpdate = bookRepository.findById(id).get();
    bookToUpdate.setTitle(book.getTitle());
    bookToUpdate.setAuthor(book.getAuthor());
    return bookRepository.save(bookToUpdate);
  }

  @DeleteMapping("books/{id}")
  public boolean delete(@PathVariable int id) {
    bookRepository.deleteById(id);
    return true;
  }
}
