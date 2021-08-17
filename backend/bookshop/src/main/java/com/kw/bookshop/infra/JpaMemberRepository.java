package com.kw.bookshop.infra;

import com.kw.bookshop.domain.Member;
import com.kw.bookshop.domain.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {}
