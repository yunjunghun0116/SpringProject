package com.junghun.startspring.repository;

import com.junghun.startspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{

    //store : static이기 때문에 싱글톤 패턴에 의해 하나의 인스턴스만 생성
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; //시스템에 저장하기위한 id(순서로)

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
