package com.cap.food.delivery.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cap.food.delivery.model.Order;
import com.cap.food.delivery.model.OrderStatusUpdateMessage;
import com.cap.food.delivery.service.OrderService;


@RestController
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	private static final String DEFAULT_ORDER_SORT_BY = "creationTime";
    private static final String DEFAULT_ORDER_SORT_DIR = "desc";

    private static final String REQUEST_TIMEOUT_RESPONSE = "Sorry, server is busy. Please try again later.";

	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * @param order
	 * @return
	 */

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public Order placeOrder(@RequestBody Order order) {
		logger.info("----placeOrder Controller method -----");
		return orderService.placeOrder(order);
	}
	

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	
	// ------------ Get order by order id ----------------------------------------------------------

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Order findOrderId(@PathVariable("orderId") int orderId) {
    	logger.info("----findOrderId Controller method -----");
        return orderService.findOrderId(orderId);
    }
    
    
    
    //------------ Get all the orders placed by one user --------------------------------------------

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Order>> findUserOrders(@PathVariable("userId") String userId) {
    	logger.info("----findOrderId Controller method -----");
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }


    // ----------- update order status ------------------------------------------------------------------------

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.PUT)
    public void updateOrderStatus(@PathVariable("orderId") int orderId,
                                  @RequestBody OrderStatusUpdateMessage orderStatusUpdateMessage) {
        this.orderService.updateOrderStatus(orderId, orderStatusUpdateMessage);
    }


}
