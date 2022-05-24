package com.junghun.startspring.controller;


public class MemberForm {
    private String name;
    //이것은 createMemberForm.html의 input(name = "name")에 해당되어 값이 매칭되고
    //매칭된 값에 따라서 넘겨줄때 자동적으로 setName()을 실행해주는데, html의 name 프로퍼티가 이름이 같아야함


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
