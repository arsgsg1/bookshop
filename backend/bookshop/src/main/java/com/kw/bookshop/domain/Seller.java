package com.kw.bookshop.domain;

import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Seller extends ShopUser {
  private long benefit;

  @OneToMany(
      mappedBy = "seller",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Book> books = new ArrayList<>();

  protected Seller() {}
  ;

  public Seller(String name, String email, String loginId, String password) {
    super.setName(name);
    super.setEmail(email);
    super.setLoginId(loginId);
    super.setPassword(password);
    this.benefit = 0;
  }

  public void registerBookForSale(Book book) {
    book.changeSeller(this);
    this.books.add(book);
  }

  public boolean deleteBookById(Long id) {
    return books.removeIf(b -> b.getId() == id);
  }

  public void saleBook(Book book) {
    this.benefit += book.getPrice();
  }
}
