package com.junghun.startspring.service;

import com.junghun.startspring.domain.Member;
import com.junghun.startspring.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void register(){
        //given
        Member member = new Member();
        member.setName("springTest");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(),findMember.getName());
    }

    @Test
    public void duplicatedCheck(){
        //given
        Member member = new Member();
        member.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,()->memberService.join(member2));
        Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다.");



    }

}
