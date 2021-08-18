package com.example.Transaction.service;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {

    private final MemberService memberService;

    @Autowired
    public MemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public void findById() {

    }
}
