package com.kw.bookshop.infra;

import com.kw.bookshop.domain.ShopUser;
import com.kw.bookshop.domain.ShopUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShopUserRepository extends JpaRepository<ShopUser, Long>, ShopUserRepository {}
