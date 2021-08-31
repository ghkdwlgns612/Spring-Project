package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional(readOnly = true)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void orderTest() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook(10000, 10, "홍길동전");
        //when
        int orderCount = 2;
        Long Id = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(Id);
        assertEquals(OrderStatus.ORDER, order.getStatus(), "상품 주문 시 상태는 ORDER");
        assertEquals(1, order.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, order.getTotalPrice(), "주문 가격 = 가격 * 수량");
        assertEquals(8, book.getStockQuantity(), "상품 주문 시 수량이 줄어야함");
    }


    @Test
    public void stockHighLimitTest() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook(10000, 10, "홍길동전");
        //when
        int orderCount = 11;
        //then
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            Long Id = orderService.order(member.getId(), book.getId(), orderCount);
        });
    }

    @Test
    public void cancelTest() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook(10000, 10, "시골쥐JPA");
        int orderCount = 2;
        Long Id = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(Id);
        //then
        Order order = orderRepository.findOne(Id);
        assertEquals(order.getStatus(),OrderStatus.CANCEL,"상태 확인");
        assertEquals(book.getStockQuantity(),10,"재고 원복");
    }

    private Book createBook(int price, int stockQuantity, String name) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","광화문","123-123"));
        em.persist(member);
        return member;
    }

}