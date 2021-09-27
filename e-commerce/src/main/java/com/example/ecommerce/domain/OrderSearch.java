package com.example.ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String userName;
    private OrderStatus status;

    public OrderSearch(String userName, OrderStatus status) {
        this.userName = userName;
        this.status = status;
    }

    public OrderSearch() {

    }
}
