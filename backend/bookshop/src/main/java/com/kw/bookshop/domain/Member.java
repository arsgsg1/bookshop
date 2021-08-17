package com.kw.bookshop.domain;

import javax.persistence.Entity;

@Entity
public class Member extends ShopUser {
  protected Member() {}
  ;

  private long point;

  public Member(String name, String email, String loginId, String password) {
    super.setName(name);
    super.setEmail(email);
    super.setLoginId(loginId);
    super.setPassword(password);
    this.point = 0;
  }

  public void purchaseBook(Book book) {
    if (this.point < book.getPrice()) {
      throw new IllegalArgumentException("잔여 포인트가 부족합니다.");
    }
    book.getSeller().saleBook(book);
    this.point -= book.getPrice();
  }
}
