package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.AddressResponseDTO;
import com.stationary.demo.dto.OrderResponseDTO;
import com.stationary.demo.dto.ProductResponseDTO;
import com.stationary.demo.entities.Address;
import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.AddressRepository;
import com.stationary.demo.repos.OrderRepository;
import com.stationary.demo.repos.ProductStatusRepository;
import com.stationary.demo.repos.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserServices userServices;
	
	@Autowired
	GenerateRandomStringService randomString;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductStatusRepository productStatusRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public OrderResponseDTO saveOrder(List<Product> product) {
		
		User user = userServices.getUser();
		OrderResponseDTO orderdto = new OrderResponseDTO();
		List<ProductResponseDTO> productsdto = new ArrayList<ProductResponseDTO>();
		Order order = new Order();
		order.setProduct(product);
		order.setDate(new Date());
		order.setUser(user);
		order.setId(randomString.generateRandomString());
		order = orderRepository.save(order);
		
		for (Product prod : product)
			productStatusRepository.saveRow("Placed", user.getId(), prod.getId(), order.getId());
		
		for (Product prod : product) {
			ProductResponseDTO productdto = new ProductResponseDTO();
			productdto.setName(prod.getName());
			productdto.setBrand(prod.getBrand());
			productdto.setDescription(prod.getDescription());
			productdto.setPrice(prod.getPrice());
			productdto.setQuantity(prod.getQuantity());
			productsdto.add(productdto);
		}
		orderdto.setId(order.getId());
		orderdto.setProducts(productsdto);
		orderdto.setUser_id(user.getId());
		Address address = addressRepository.findByDefaultAddress();
		AddressResponseDTO addressdto = new AddressResponseDTO();
		addressdto.setCity(address.getCity());
		addressdto.setCountry(address.getCountry());
		addressdto.setState(address.getState());
		addressdto.setStreet(address.getStreet());
		addressdto.setZipcode(address.getZipcode());
		addressdto.setId(address.getId());
		orderdto.setAddress(addressdto);
		return orderdto;
	}

	@Override
	public List<OrderResponseDTO> getOrder() {
		
		List<OrderResponseDTO> ordersdto = new ArrayList<OrderResponseDTO>();
		List<ProductResponseDTO> productsdto = new ArrayList<ProductResponseDTO>();
		User user = userServices.getUser();
		List<Order> orders = user.getOrder();
		
		return null;
	}

	@Override
	public OrderResponseDTO updateOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(String order_id) {
		// TODO Auto-generated method stub
		
	}


}
