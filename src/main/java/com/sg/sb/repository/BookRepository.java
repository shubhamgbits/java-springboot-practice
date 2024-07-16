package com.sg.sb.repository;

import com.sg.sb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.rating > :rating")
    List<Book> getHighRatedBooks(String rating);
}
