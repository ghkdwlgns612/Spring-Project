package com.example.Transaction.source;

import com.example.Transaction.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Slf4j
public class DataSourceManager {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberRepository memberRepository;

    public void Test() {
        System.out.println("dataSource = " + dataSource);
    }



}
