package com.kw.bookshop.infra;

import com.kw.bookshop.domain.Book;
import com.kw.bookshop.domain.BookRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long>, BookRepository {
  List<Book> findAll();

  Optional<Book> findById(Long id);
}
