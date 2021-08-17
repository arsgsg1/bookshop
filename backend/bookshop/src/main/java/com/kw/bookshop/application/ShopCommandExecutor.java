package com.kw.bookshop.application;

import com.kw.bookshop.application.dto.request.CreateOrUpdateBookDto;
import com.kw.bookshop.domain.Book;
import com.kw.bookshop.domain.Seller;
import com.kw.bookshop.domain.MemberRepository;
import com.kw.bookshop.domain.SellerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShopCommandExecutor {
  private final MemberRepository userRepository;
  private final SellerRepository sellerRepository;

  public ShopCommandExecutor(
      final MemberRepository userRepository, final SellerRepository sellerRepository) {
    this.userRepository = userRepository;
    this.sellerRepository = sellerRepository;
  }

  public Seller findSellerOrThrow(long sellerId) {
    return sellerRepository
        .findById(sellerId)
        .orElseThrow(() -> new IllegalArgumentException("판매자 ID가 없습니다."));
  }

  @Transactional
  public long createBook(CreateOrUpdateBookDto dto) {
    Book toCreate = new Book(dto.getName(), dto.getDescription(), dto.getPrice());
    Seller seller = findSellerOrThrow(dto.getSellerId());
    seller.registerBookForSale(toCreate);
    return sellerRepository.save(seller).getId();
  }

  @Transactional
  public boolean deleteBookById(long sellerId, long bookId) {
    return findSellerOrThrow(sellerId).deleteBookById(bookId);
  }

  @Transactional
  public void updateBookInfoById(long sellerId, long bookId, CreateOrUpdateBookDto dto) {
    Seller seller = findSellerOrThrow(sellerId);
    seller.getBooks().stream()
        .filter(b -> b.getId() == bookId)
        .forEach(b -> b.updateInfo(dto.getName(), dto.getDescription(), dto.getPrice()));
  }
}
