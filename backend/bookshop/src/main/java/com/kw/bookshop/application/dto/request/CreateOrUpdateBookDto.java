package com.kw.bookshop.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateBookDto {
  private long price;
  private String name;
  private String description;
  private long sellerId; // todo session
}
