package com.kw.bookshop.domain;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
  List<Book> findAll();

  Optional<Book> findById(Long id);
}
