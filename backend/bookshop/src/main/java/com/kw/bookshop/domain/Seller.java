package com.kw.bookshop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends ShopUser {
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
  }
}
