package com.example.Transaction;

import com.example.Transaction.domain.Member;
import com.example.Transaction.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TransactionApplicationTests {

	Logger log = (Logger) LoggerFactory.getLogger(TransactionApplicationTests.class);

	@Autowired
	private DataSource dataSource;
	@Autowired
	private MemberRepository memberRepository;

//	@Test
//	void Test1() {
//		System.out.println("data = " + dataSource);
//	}


	@Test
	void findByIdTest() {
		Optional<Member> byId = memberRepository.findById(1L);
		System.out.println("byId = " + byId.get().getName());
	}
}
