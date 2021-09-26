package com.example.ecommerce.controller;

import com.example.ecommerce.domain.Item;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderSearch;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.service.ItemService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ItemService itemService;
    private final UserService userService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Item> items = itemService.findItems();
        List<User> users = userService.findUsers();

        model.addAttribute("items",items);
        model.addAttribute("users",users);

        return "orders/createForm";
    }

    @PostMapping("/order")
    public String create(@RequestParam("userId") Long userId,
                         @RequestParam("itemId") Long itemId,
                         @RequestParam("count") int count) {
        orderService.order(userId,itemId,count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrdersByStatusAndName(orderSearch.getStatus(), orderSearch.getUserName());
        model.addAttribute("orders", orders);
        return "orders/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
