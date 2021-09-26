package com.example.ecommerce.service;

import com.example.ecommerce.domain.*;
import com.example.ecommerce.repository.ItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long userId, Long itemId, int count) {
        User user = userRepository.findOne(userId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(user.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        Order order = Order.createOrder(user,delivery,orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    @Transactional
    public List<Order> findOrdersByStatusAndName(OrderStatus status, String name) {
        OrderSearch orderSearch = new OrderSearch(name, status);
        return orderRepository.findAll(orderSearch);
    }
}
