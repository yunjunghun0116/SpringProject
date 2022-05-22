package com.junghun.startspring.service;

import com.junghun.startspring.domain.Member;
import com.junghun.startspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //이걸 사용해서 memberService가 초기화되는 이유는 memberService와 memberRepository가 결국 둘다 최종적으로 MemoryMemberRepository를 참조하는데
    //이 참조된 객체의 store, 즉 저장소자체는 하나, 싱글턴에 의해 하나의 인스턴스만 생성되기 때문에 memberRepository에서 초기화 시켜주어도 memberService의 store도 초기화된다
    //static -> 객체Level이 아닌 ClassLevel에 생성되기때문에 오직 하나로써 존재가 가능한것이다
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");
        // when
        Long saveId = memberService.join(member);
        // then
        assertThat(memberService.findOne(saveId).get()).isEqualTo(member);
    }

    @Test
    void duplicateException(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        //Jupiter-Assertions를 통해 error타입을 가지고 확인이 가능하다
        Assertions.assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        /*
            try{
                memberService.join(member2);
                fail("예외가 발생해야 합니다");
            }catch(IllegalStateException e){
                assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            }
        */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}