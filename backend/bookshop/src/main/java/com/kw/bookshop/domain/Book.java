package com.kw.bookshop.domain;

import javax.persistence.*;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Seller seller;
}
