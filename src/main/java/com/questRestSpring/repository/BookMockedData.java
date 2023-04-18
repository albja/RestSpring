package com.questRestSpring.repository;

import com.questRestSpring.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookMockedData {

  private List<Book> books;

  private static BookMockedData instance = null;

  public static BookMockedData getInstance() {
    if (instance == null) {
      instance = new BookMockedData();
    }
    return instance;
  }

  public BookMockedData() {
    books = new ArrayList<Book>();
    books.add(
      new Book(
        1,
        "Notre Dame de Paris",
        "Victor Hugo",
        "Un roméo et Juliette avec un moche"
      )
    );
    books.add(
      new Book(
        2,
        "A la recherche du temps perdu",
        "Marcel Proust",
        "Cela sent la madeleine"
      )
    );
    books.add(
      new Book(
        3,
        "Le Hobbit",
        "JJR Tolkien",
        "Les péripéties de 14 nains, un magicien et d'autres joyeux lurons"
      )
    );
  }

  // return all blogs
  public List<Book> fetchBooks() {
    return books;
  }

  // return book by id
  public Book getBookById(int id) {
    for (Book b : books) {
      if (b.getId() == id) {
        return b;
      }
    }
    return null;
  }

  // search book by text
  public List<Book> searchBooks(String searchTerm) {
    List<Book> searchedBooks = new ArrayList<Book>();
    for (Book b : books) {
      if (
        b.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
        b.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())
      ) {
        searchedBooks.add(b);
      }
    }

    return searchedBooks;
  }

  // create book
  public Book createBook(
    int id,
    String title,
    String author,
    String description
  ) {
    Book newBook = new Book(id, title, author, description);
    books.add(newBook);
    return newBook;
  }

  // update blog
  public Book updateBook(
    int id,
    String title,
    String author,
    String description
  ) {
    for (Book b : books) {
      if (b.getId() == id) {
        int bookIndex = books.indexOf(b);
        b.setTitle(title);
        b.setAuthor(author);
        b.setDescription(description);
        books.set(bookIndex, b);
        return b;
      }
    }

    return null;
  }

  // delete book by id
  public boolean delete(int id) {
    int bookIndex = -1;
    for (Book b : books) {
      if (b.getId() == id) {
        bookIndex = books.indexOf(b);
        continue;
      }
    }
    if (bookIndex > -1) {
      books.remove(bookIndex);
    }
    return true;
  }
}
