package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.AddressResponseDTO;
import com.stationary.demo.dto.OrderRequest;
import com.stationary.demo.dto.OrderResponseDTO;
import com.stationary.demo.dto.ProductResponseDTO;
import com.stationary.demo.entities.Address;
import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	UserServices userServices;

	@Autowired
	GenerateRandomStringService randomString;

	@Autowired
	ProductService productService;

	@Autowired
	AddressService addressService;

	@Autowired
	ProductStatusService productStatusService;

	@Autowired
	OrderRepository orderRepository;

	@Override
	public OrderResponseDTO saveOrder(OrderRequest orderReq) {
		
		LOGGER.info("Inside saveOrder method with parameter - " + orderReq);
		Address address = null;
		User user = userServices.getUser();
		
		LOGGER.info("Get the logged in user - " + user);
		OrderResponseDTO orderdto = new OrderResponseDTO();
		List<ProductResponseDTO> productsdto = new ArrayList<ProductResponseDTO>();
		Order order = new Order();
		
		LOGGER.info("Setting the orderReq value in Order class");
		order.setProduct(orderReq.getProducts());
		order.setDate(new Date());
		order.setUser(user);
		
		LOGGER.info("Set the user in Order class");
		AddressResponseDTO addressdto = null;
		order.setId(randomString.generateRandomString());
		
		LOGGER.info("Set the random Id in order");
		LOGGER.info("save the product status by calling method saveProductStatus with parameter order - " + order
				+ " user_id - " + user.getId() + " status - Placed");
		productStatusService.saveProductStatus(order, user.getId(), "Placed");
		
		LOGGER.info(
				"save product class value to productResponseDto class, calling method saveProductToProductResponseDto with parameter user - "
						+ user + " order - " + order + " status - Placed");
		productsdto = productStatusService.saveProductToProductResponseDto(user, order, "Placed");
		
		LOGGER.info("Returned from saveProductToProductResponseDto with result - " + productsdto);
		LOGGER.info("Set the Order class value to the OrderResponseDto class");
		
		orderdto.setId(order.getId());
		orderdto.setProducts(productsdto);
		orderdto.setUser_id(user.getId());
		
		LOGGER.info("Invoking getOrderAddress method with parameter - " + orderReq);
		address = addressService.getOrderAddress(orderReq);
		LOGGER.info("Returned from getOrderAddress with result - " + address);
		LOGGER.info("Invoking saveAddressToAddressResponseDto with parameter - " + address);
		addressdto = addressService.saveAddressToAddressResponseDTO(address);
		LOGGER.info("Returned from saveAddressToAddressResponseDto with result - " + addressdto);
		LOGGER.info("Set the addressdto to Orderdto");
		orderdto.setAddress(addressdto);
		LOGGER.info("Set the address to order");
		order.setAddress(address);
		LOGGER.info("Save the Order to DB");
		order = orderRepository.save(order);
		LOGGER.info("Successfully saved the Order to DB");
		LOGGER.info("return the orderdto to OrderController");
		return orderdto;

	}

	@Override
	public List<OrderResponseDTO> getOrder() {
		
		LOGGER.info("Inside getOrder method");
		User user = userServices.getUser();
		LOGGER.info("Get the logged in user - " + user);
		LOGGER.info("Find the Order by user");
		List<Order> orderList = orderRepository.findByUser(user);
		LOGGER.info("OrderList - " + orderList);
		List<OrderResponseDTO> ordersdtoList = new ArrayList<OrderResponseDTO>();
		OrderResponseDTO ordersdto = new OrderResponseDTO();
		List<ProductResponseDTO> productsdto = new ArrayList<ProductResponseDTO>();

		LOGGER.info("Get the product of each order and save it into ProductResponseDto");
		for (Order order : orderList) {
			productsdto = productStatusService.saveProductToProductResponseDto(user, order, null);
			ordersdto.setId(order.getId());
			ordersdto.setProducts(productsdto);
			ordersdto.setUser_id(user.getId());
			ordersdtoList.add(ordersdto);
		}
		LOGGER.info("Return the orderdtolist to OrderController");
		return ordersdtoList;
	}

	@Override
	public String updateOrder(String status, String order_id, Long product_id, OrderRequest orderReq) {
		User user = userServices.getUser();
		productStatusService.updateProductStatus(status, user.getId(), order_id, product_id);

		return "Order Cancelled Successfully";
	}

	@Override
	public OrderResponseDTO exchangeOrder(OrderRequest orderReq) {
		Address address = null;
		User user = userServices.getUser();
		OrderResponseDTO orderdto = new OrderResponseDTO();
		List<ProductResponseDTO> productsdto = new ArrayList<ProductResponseDTO>();
		Order order = new Order();
		order.setProduct(orderReq.getProducts());
		order.setDate(new Date());
		order.setUser(user);
		AddressResponseDTO addressdto = null;
		order.setId(randomString.generateRandomString());
		productStatusService.saveProductStatus(order, user.getId(), "Exchange");
		productsdto = productStatusService.saveProductToProductResponseDto(user, order, "Exchange");
		orderdto.setId(order.getId());
		orderdto.setProducts(productsdto);
		orderdto.setUser_id(user.getId());
		address = addressService.getOrderAddress(orderReq);
		addressdto = addressService.saveAddressToAddressResponseDTO(address);
		orderdto.setAddress(addressdto);
		order.setAddress(address);
		order = orderRepository.save(order);
		return orderdto;
	}

	@Override
	public void deleteOrder(String order_id) {
		// TODO Auto-generated method stub

	}

}
