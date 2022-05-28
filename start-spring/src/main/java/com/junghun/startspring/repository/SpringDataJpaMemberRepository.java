package com.junghun.startspring.repository;

import com.junghun.startspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
    //JpaRepository<Type(Class), ID(구별자)>

    @Override
    Optional<Member> findByName(String name);

    @Override
    Optional<Member> findById(Long id);
}
