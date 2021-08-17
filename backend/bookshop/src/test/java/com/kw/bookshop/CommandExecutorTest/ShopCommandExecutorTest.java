package com.kw.bookshop.CommandExecutorTest;

import com.kw.bookshop.application.ShopCommandExecutor;
import com.kw.bookshop.application.dto.request.CreateOrUpdateBookDto;
import com.kw.bookshop.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext.*;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD) // 각 테스트가 다른 테스트에 영향을 끼치지 않게 하기 위함.
public class ShopCommandExecutorTest {
  @Autowired private ShopCommandExecutor commandExecutor;
  @Autowired private BookRepository bookRepository;
  @Autowired private SellerRepository sellerRepository;
  private final Seller seller = new Seller("s1", "sese@gmail.com", "erer", "123123");
  private Long deleteBookId;
  private final Long listSize = 3L;

  @Transactional
  @BeforeEach
  public void setup() {
    for (long i = 1; i <= listSize; i++) {
      seller.registerBookForSale(new Book("b" + i, "d" + i, 100L * i));
    }
    sellerRepository.save(seller);
    deleteBookId = seller.getBooks().get(1).getId();
  }

  @DisplayName("판매 책 등록")
  @Test
  public void createTest() {
    // given
    CreateOrUpdateBookDto dto = new CreateOrUpdateBookDto(10000L, "b4", "d4", seller.getId());
    // when
    commandExecutor.createBook(dto);
    // then
    Assertions.assertEquals(listSize + 1, bookRepository.findAll().size());
  }

  @DisplayName("판매 책 삭제")
  @Test
  public void deleteTest() {
    // given
    Seller toDelete = sellerRepository.findById(seller.getId()).get();
    // when
    commandExecutor.deleteBookById(seller.getId(), deleteBookId);
    // then
    Assertions.assertEquals(listSize - 1, bookRepository.findAll().size());
  }

  @DisplayName("판매 책 업데이트")
  @Test
  public void updateTest() {
    // given
    Seller toUpdate = sellerRepository.findById(seller.getId()).get();
    Long updateBookId = 1L;
    String updateName = "b100";
    String updateDesc = "d100";
    long updatePrice = 10L;
    CreateOrUpdateBookDto dto =
        new CreateOrUpdateBookDto(updatePrice, updateName, updateDesc, seller.getId());
    // when
    commandExecutor.updateBookInfoById(seller.getId(), updateBookId, dto);
    // then
    Book updatedBook = bookRepository.findById(updateBookId).get();
    Assertions.assertEquals(updateName, updatedBook.getName());
    Assertions.assertEquals(updateDesc, updatedBook.getDescription());
    Assertions.assertEquals(updatePrice, updatedBook.getPrice());
  }
}
