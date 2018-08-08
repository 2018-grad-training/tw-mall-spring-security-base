package cn.thoughtworks.twMall.controller;

import cn.thoughtworks.twMall.entity.BasicUserDetail;
import cn.thoughtworks.twMall.entity.Order;
import cn.thoughtworks.twMall.exception.OrderNotFoundException;
import cn.thoughtworks.twMall.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Log
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> get(@PathVariable Long orderId) {
        Order order = orderService.get(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("")
    public ResponseEntity getAll(Authentication authentication) {
        BasicUserDetail userDetail = (BasicUserDetail) authentication.getPrincipal();
        log.info(String.format("%s", userDetail.getId()));
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private void exceptionHandler(OrderNotFoundException ex) {}
}
