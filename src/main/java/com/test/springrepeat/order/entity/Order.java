package com.test.springrepeat.order.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "repeat/order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_price")
    private Integer orderPrice;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @Column(name = "order_create_at")
    private LocalDateTime orderCreateAt;

    @Column(name = "order_update_at")
    private LocalDateTime orderUpdateAt;

    public Order() {
    }

    private Order(Integer orderId, String orderName, Integer orderPrice, Integer orderQuantity, LocalDateTime orderCreateAt, LocalDateTime orderUpdateAt) {

        this.orderId = orderId;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.orderCreateAt = orderCreateAt;
        this.orderUpdateAt = orderUpdateAt;
    }


    public static  Order builder(){
        return new Order();
    }

    public Order orderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Order orderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public Order orderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public Order orderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
        return this;
    }


    public Order orderCreateAt(LocalDateTime orderCreateAt) {
        this.orderCreateAt = orderCreateAt;
        return this;
    }


    public Order orderUpdateAt(LocalDateTime orderUpdateAt) {
        this.orderUpdateAt = orderUpdateAt;
        return this;
    }

    public Order build(){
        return new Order(orderId,orderName,orderPrice,orderQuantity,orderCreateAt,orderUpdateAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderQuantity=" + orderQuantity +
                ", orderCreateAt='" + orderCreateAt + '\'' +
                ", orderUpdateAt='" + orderUpdateAt + '\'' +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public LocalDateTime getOrderCreateAt() {
        return orderCreateAt;
    }

    public LocalDateTime getOrderUpdateAt() {
        return orderUpdateAt;
    }
}