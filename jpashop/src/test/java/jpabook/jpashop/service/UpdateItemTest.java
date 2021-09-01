package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateItemTest {
    @Autowired
    EntityManager em;

    @Test
    public void 테스트() throws Exception{
        Book book = em.find(Book.class, 1L);
        //변경감지 - 더티체킹
        book.setName("jihuhwan");
    }
}
