package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.ProductResponseDTO;
import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.ProductStatus;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.ProductStatusRepository;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {

	@Autowired
	ProductStatusRepository productStatusRepository;
	
	@Override
	public void saveProductStatus(Order order, long user_id, String status) {
		for (Product prod : order.getProduct())
			productStatusRepository.saveRow("Placed", user_id, prod.getId(), order.getId());		
	}
	
	@Override
	public List<ProductResponseDTO> saveProductToProductResponseDto(User user, Order order, String status) {
		List<ProductResponseDTO> productsdto = new ArrayList<>();
		ProductStatus productStatus = new ProductStatus();
		for (Product prod : order.getProduct()) {
			ProductResponseDTO productdto = new ProductResponseDTO();
			productdto.setId(prod.getId());
			productdto.setName(prod.getName());
			productdto.setBrand(prod.getBrand());
			productdto.setDescription(prod.getDescription());
			productdto.setPrice(prod.getPrice());
			productdto.setQuantity(prod.getQuantity());
			productStatus.setOrder_id(order.getId());
			productStatus.setProduct_id(prod.getId());
			productStatus.setUser_id(user.getId());
			if(status==null) 
				productStatus.setStatus("Placed");
			else
				productStatus = productStatusRepository.findByUserOrderProduct(user.getId(),order.getId(),prod.getId());
			productdto.setStatus(productStatus);
			productsdto.add(productdto);
		}
		return productsdto;
	}
	
	@Override
	public ProductStatus updateProductStatus(String status, long user_id,String order_id,long product_id) {
		ProductStatus productStatus = productStatusRepository.findByUserOrderProduct(user_id,order_id,product_id);
		if(productStatus!=null && status.equalsIgnoreCase("Cancel")) {
			if(productStatus.getStatus().equalsIgnoreCase("Placed")) {
				productStatus.setStatus(status);
				productStatusRepository.save(productStatus);
			}
		}
		return productStatus;
	}

}
