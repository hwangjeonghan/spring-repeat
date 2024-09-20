package com.test.springrepeat.order.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Integer  orderId;
    private String orderName;
    private Integer orderPrice;
    private Integer orderQuantity;
    private LocalDateTime orderCreateAt;
    private LocalDateTime orderUpdateAt;

    public OrderDTO() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public LocalDateTime getOrderCreateAt() {
        return orderCreateAt;
    }

    public void setOrderCreateAt(LocalDateTime orderCreateAt) {
        this.orderCreateAt = orderCreateAt;
    }

    public LocalDateTime getOrderUpdateAt() {
        return orderUpdateAt;
    }

    public void setOrderUpdateAt(LocalDateTime orderUpdateAt) {
        this.orderUpdateAt = orderUpdateAt;
    }

    public OrderDTO(Integer orderId, String orderName, Integer orderPrice, Integer orderQuantity, LocalDateTime orderCreateAt, LocalDateTime orderUpdateAt) {

        this.orderId = orderId;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.orderCreateAt = orderCreateAt;
        this.orderUpdateAt = orderUpdateAt;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderQuantity=" + orderQuantity +
                ", orderCreateAt='" + orderCreateAt + '\'' +
                ", orderUpdateAt='" + orderUpdateAt + '\'' +
                '}';
    }
}
