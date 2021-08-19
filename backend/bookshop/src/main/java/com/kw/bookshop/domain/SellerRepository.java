package com.kw.bookshop.domain;

import java.util.Optional;

public interface SellerRepository {
  Optional<Seller> findById(Long id);

  Seller save(Seller seller);

  void deleteById(Long id);
}
