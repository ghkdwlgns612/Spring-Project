package com.example.ecommerce;

import com.example.ecommerce.domain.*;
import com.example.ecommerce.domain.items.Book;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@AllArgsConstructor
public class InitDb {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            User user = createUser("userA", "서울", "1", 1111);
            em.persist(user);
            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);
            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            Order order = Order.createOrder(user, createDelivery(user), orderItem1, orderItem2);
            em.persist(order);
        }
        public void dbInit2() {
            User user = createUser("userB", "진주", "2", 2222);
            em.persist(user);
            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);
            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);
            Delivery delivery = createDelivery(user);
            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            Order order = Order.createOrder(user, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private User createUser(String name, String city, String street, int zipcode) {
            User user = new User();
            user.setName(name);
            user.setAddress(new Address(city, street, zipcode));
            return user;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Delivery createDelivery(User user) {
            Delivery delivery = new Delivery();
            delivery.setAddress(user.getAddress());
            return delivery;
        }
    }
}