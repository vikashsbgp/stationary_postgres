package com.stationary.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.dto.OrderRequest;
import com.stationary.demo.service.OrderService;
import com.stationary.demo.service.UserServices;
import com.stationary.demo.utilities.ResponseLibrary;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	UserServices userServices;
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<ResponseLibrary> placeOrder(@RequestBody OrderRequest orderReq) {
		LOGGER.info("API for placing order with request body " + orderReq);
		ResponseLibrary res = new ResponseLibrary();
		LOGGER.info("Setting the data in responseLibrary");
		LOGGER.info("Calling saveOrder method with orderReq parameter " + orderReq);
		res.setData(orderService.saveOrder(orderReq));
		res.setError(false);
		res.setMessage("Order Created Successfully");
		res.setStatus(200);
		LOGGER.info("Return the ResponseEntity to the response body " + res);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/order")
	public ResponseEntity<ResponseLibrary> getOrder() {
		LOGGER.info("API to fetch all order");
		ResponseLibrary res = new ResponseLibrary();
		LOGGER.info("Setting the data in responseLibrary");
		LOGGER.info("Calling getOrder method");
		res.setData(orderService.getOrder());
		res.setError(false);
		res.setMessage("Order Fetched Successfully");
		res.setStatus(200);
		LOGGER.info("Return the ResponseEntity to the response body " + res);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/order")
	public ResponseEntity<ResponseLibrary> updateOrder(@RequestParam String status ,@RequestParam String order_id, @RequestParam Long product_id, @RequestBody(required = false) OrderRequest orderReq) {
		LOGGER.info("API to update order with following parameter status - " + status + " order_id - " + order_id + " product_id - " + product_id + " OrderRequest - " + orderReq);
		ResponseLibrary res = new ResponseLibrary();
		LOGGER.info("Setting the data in responseLibrary");
		res.setData(null);
		res.setError(false);
		LOGGER.info("Calling updateOrder method");
		res.setMessage(orderService.updateOrder(status,order_id,product_id,orderReq));
		res.setStatus(200);
		LOGGER.info("Return the ResponseEntity to the response body " + res);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);		 
	}
	
	@DeleteMapping("/{id}/order")
	public ResponseEntity<ResponseLibrary> deleteOrder(@PathVariable("id") String order_id) {
		LOGGER.info("API to delete order with parameter order id - " + order_id);
		LOGGER.info("Calling deleteOrder method");
		orderService.deleteOrder(order_id);
		ResponseLibrary res = new ResponseLibrary();
		LOGGER.info("Setting the data in responseLibrary");
		res.setData(null);
		res.setError(false);
		res.setMessage("Order deleted Successfully");
		res.setStatus(200);
		LOGGER.info("Return the ResponseEntity to the response body " + res);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}

}
