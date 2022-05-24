package com.junghun.startspring.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Entity //JPA가 직접 관리하는 엔티티가 된다!라는 의미
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 직접 생성해주는건 IDENTITY
    private Long id;
    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
