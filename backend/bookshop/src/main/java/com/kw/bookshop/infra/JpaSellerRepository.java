package com.kw.bookshop.infra;

import com.kw.bookshop.domain.Seller;
import com.kw.bookshop.domain.SellerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaSellerRepository extends JpaRepository<Seller, Long>, SellerRepository {
  Optional<Seller> findById(Long id);

  Seller save(Seller seller);
}
