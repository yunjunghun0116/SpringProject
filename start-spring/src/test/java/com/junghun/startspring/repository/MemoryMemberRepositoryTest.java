package com.junghun.startspring.repository;

import com.junghun.startspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest{

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 무슨 코드가 먼저 수행될지 모르기때문에 각각의 수행이 끝날때마다 비워주는 작업이필요
    //AfterEach는 매 테스트 코드가 끝날때마다 호출
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Optional<Member> result =  repository.findById(member.getId());

        assertThat(member).isEqualTo(result.get());
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Optional<Member> result = repository.findByName("spring1");
        assertThat(member1).isEqualTo(result.get());
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
