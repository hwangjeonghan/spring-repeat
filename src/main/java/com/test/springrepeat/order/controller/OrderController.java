package com.test.springrepeat.order.controller;

import com.test.springrepeat.order.dto.OrderDTO;
import com.test.springrepeat.order.entity.Order;
import com.test.springrepeat.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>>getALLOrders(){
        List<Order> orders = orderService.findAllOrders();
        if (orders.isEmpty()){
            return ResponseEntity.status(404)
                    .header("message","등록된 상품이 없습니다.")
                    .build();
        }return ResponseEntity.ok(orders);
    }
    // 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<Order>getOrderById(@PathVariable Integer orderId){
        try {
            Optional<Order> getOrder = orderService.findOrderById(orderId);
            if (getOrder.isPresent()) {
                return ResponseEntity.ok(getOrder.get());
            } else {
                return ResponseEntity.status(404)
                        .header("message", "조회할 상품의 데이터가 없습니다.")
                        .build();
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(400)
                    .header("message",e.getMessage())
                    .build();
        }
    }
    // 등록
    @PostMapping("/create")
    public ResponseEntity<Order>createOrder(@RequestBody OrderDTO orderDTO){
        try {
            Optional<Order> saveOrder = orderService.saveOrder(orderDTO);
            if (saveOrder.isPresent()){
                return ResponseEntity.ok(saveOrder.get());
            }else {
                return ResponseEntity.status(500)
                        .header("message", "주문저장이 실패되었습니다.")
                        .build();
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(400)
                    .header("message",e.getMessage())
                    .build();
        }
    }
    @PutMapping("/update/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer orderId, @RequestBody OrderDTO orderDTO){
        try {
            Order updateOrder = orderService.updateOrder(orderId,orderDTO);
            return ResponseEntity.ok(updateOrder);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(400)
                    .header("message",e.getMessage())
                    .build();
        }catch (Exception e){
            return ResponseEntity.status(500)
                    .header("mssage","주문 정보가 업데이트 되었습니다.")
                    .build();
        }
    }
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId){
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(400)
                    .header("message",e.getMessage())
                    .build();
        }catch (Exception e){
            return ResponseEntity.status(500)
                    .header("message","주문 삭제에 실패했습니다.")
                    .build();
        }
    }


}
