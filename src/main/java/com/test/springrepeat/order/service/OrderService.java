package com.test.springrepeat.order.service;

import com.test.springrepeat.order.dto.OrderDTO;
import com.test.springrepeat.order.entity.Order;
import com.test.springrepeat.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(Integer orderId) {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("상품의 id는 없으면 안되거나 0보다 커야합니다.");
        }
        return orderRepository.findById(orderId);
    }

    @Transactional
    public Optional<Order> saveOrder(OrderDTO orderDTO){
        Order order = Order.builder()
                .orderName(orderDTO.getOrderName())
                .orderPrice(orderDTO.getOrderPrice())
                .orderQuantity(orderDTO.getOrderQuantity())
                .orderCreateAt(LocalDateTime.now())
                .build();
        validateOrder(order);
        Order saveOrder = orderRepository.save(order);
        return Optional.of(saveOrder);
    }

    @Transactional
    public Order updateOrder(Integer OrderId, OrderDTO orderDTO){
        Order findOrder = orderRepository.findById(OrderId).orElse(null);
        if (findOrder == null){
            throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다.");
        }
        Order updateOrder = Order.builder()
                .orderId(findOrder.getOrderId())
                .orderName(orderDTO.getOrderName() != null ? orderDTO.getOrderName() : findOrder.getOrderName())
                .orderQuantity(orderDTO.getOrderQuantity() != null ? orderDTO.getOrderQuantity() : findOrder.getOrderQuantity())
                .orderPrice(orderDTO.getOrderPrice() != null ? orderDTO.getOrderPrice() : findOrder.getOrderPrice())
                .orderCreateAt(findOrder.getOrderCreateAt())
                .orderUpdateAt(LocalDateTime.now())
                .build();
        validateOrder(updateOrder);
        return orderRepository.save(updateOrder);
    }
    @Transactional
    public void deleteOrder(Integer orderId){
        Order findOrder = orderRepository.findById(orderId).orElse(null);
        if (findOrder == null){
            throw new IllegalArgumentException("해당 주문을 찾을 수 없습니다.");
        }
        orderRepository.delete(findOrder);
    }









    private void validateOrder(Order order) {
        if (order.getOrderName() == null) {
            throw new IllegalArgumentException("상품의 이름은 생략될 수 없습니다.");
        }
        if (order.getOrderPrice() == null || order.getOrderPrice() < 0) {
            throw new IllegalArgumentException("상품의 가격은 음수가 될 수 없습니다.");
        }
        if (order.getOrderQuantity() == null || order.getOrderQuantity() <0){
            throw new IllegalArgumentException("상품의 수량은 음수가 될 수 없습니다.");

        }
    }
}
