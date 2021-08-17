package com.kw.bookshop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private long price;
  @ManyToOne private Seller seller;

  protected Book() {}
  ;

  public Book(String name, String description, long price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public void updateInfo(String name, String description, long price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public void changeSeller(Seller seller) {
    this.seller = seller;
  }
}
