package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o From Order o join o.user m";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if (orderSearch.getStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getUserName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000); //최대 1000건
        if (orderSearch.getStatus() != null) {
            query = query.setParameter("status", orderSearch.getStatus());
        }
        if (StringUtils.hasText(orderSearch.getUserName())) {
            query = query.setParameter("name", orderSearch.getUserName());
        }
        return query.getResultList();
    }

    public List<Order> findAllWithUserDelivery() {
        return em.createQuery(
                        "select o from Order o" +
                                " join fetch o.user u" +
                                " join fetch o.delivery d", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithItem() {
        return em.createQuery(
                "select distinct o from Order o" +
                " join fetch o.user u" +
                " join fetch o.delivery d" +
                " join fetch o.orderItems oi" +
                " join fetch oi.item i",Order.class)
                .getResultList();
    }

    public List<Order> findAllWithUserDelivery(int offset, int limit) {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.user u" +
                        " join fetch o.delivery d",Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
