package com.junghun.startspring;

import com.junghun.startspring.repository.MemberRepository;
import com.junghun.startspring.repository.MemoryMemberRepository;
import com.junghun.startspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
