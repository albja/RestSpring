package com.questRestSpring.repository;

import com.questRestSpring.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
  // custom query to search to book post by title or content
  List<Book> findByTitleContainingOrAuthorContaining(
    String text,
    String textAgain
  );
}
