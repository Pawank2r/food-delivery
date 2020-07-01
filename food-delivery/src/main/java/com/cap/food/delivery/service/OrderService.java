package com.cap.food.delivery.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.cap.food.delivery.model.Order;
import com.cap.food.delivery.model.OrderStatusUpdateMessage;

public interface OrderService {

	Order placeOrder(Order order);
	
	Order findOrderId(int orderId);

	List<Order> findByUserId(String userId);
	
    void updateOrderStatus(int orderId, OrderStatusUpdateMessage orderStatusUpdateMessage);

}
