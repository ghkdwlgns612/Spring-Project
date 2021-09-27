package com.example.ecommerce.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                "select new com.example.ecommerce.repository.OrderSimpleQueryDto(o.id, u.name, o.orderDate, o.status, d.address)" +
                        " from Order o" +
                                " join o.user u" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
