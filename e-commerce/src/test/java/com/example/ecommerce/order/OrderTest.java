package com.example.ecommerce.order;

import com.example.ecommerce.domain.*;
import com.example.ecommerce.domain.items.Book;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void orderTest() throws Exception{
        User user = createUser("jihuhwan",new Address("Seoul","sungsu",153));
        Item item = createBook("시골쥐",25000,12);
        int orderCount = 2;

        Long orderId = orderService.order(user.getId(), item.getId(), orderCount);

        Order order = orderRepository.findOne(orderId);
        Assertions.assertEquals(OrderStatus.ORDER,order.getStatus());
        Assertions.assertEquals(1, order.getOrderItems().size());
        Assertions.assertEquals(50000,order.getTotalPrice());
        Assertions.assertEquals(10,item.getStockQuantity());
   }

   @Test
   void overStockQuantityTest() {
       User user = createUser("jihuhwan",new Address("Seoul","sungsu",153));
       Item item = createBook("시골쥐",25000,12);
       int orderCount = 13;

       Assertions.assertThrows(IllegalStateException.class, () -> {
           orderService.order(user.getId(), item.getId(), orderCount);
       });
   }

   @Test
   void cancelOrder() {
       User user = createUser("jihuhwan",new Address("Seoul","sungsu",153));
       Item item = createBook("시골쥐",25000,12);
       int orderCount = 2;

       Long orderId = orderService.order(user.getId(), item.getId(), orderCount);
       orderService.cancelOrder(orderId);

       Order order = orderRepository.findOne(orderId);
       Assertions.assertEquals(OrderStatus.CANCEL,order.getStatus());
       Assertions.assertEquals(12,item.getStockQuantity());
   }

   @Test
   void findOrdersByStatusAndNameTest() {
        User user1 = createUser("jihuhwan",new Address("Seoul","sungsu",153));
        User user2 = createUser("dodo", new Address("masan","bongam",123));
        Item item1 = createBook("항아리",35000,20);
        Item item2 = createBook("시골쥐",25000,12);
        int cnt1 = 2;
        int cnt2 = 5;
        int cnt3 = 1;

       Long order1 = orderService.order(user1.getId(), item1.getId(), cnt1);
       Long order2 = orderService.order(user1.getId(), item2.getId(), cnt2);
       Long order3 = orderService.order(user2.getId(), item1.getId(), cnt3);

       List<Order> ordersByStatusAndName = orderService.findOrdersByStatusAndName(OrderStatus.ORDER, user1.getName());
       Assertions.assertEquals(2,ordersByStatusAndName.size());
   }

    private Book createBook(String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private User createUser(String name, Address address) {
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        em.persist(user);
        return user;
    }

}
