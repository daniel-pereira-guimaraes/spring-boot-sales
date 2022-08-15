package com.example.spring.boot.sales.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.boot.sales.Messages;
import com.example.spring.boot.sales.entities.Product;
import com.example.spring.boot.sales.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		//return productRepository.findAll(Sort.by(Sort.Direction.ASC, "description"));
		//return productRepository.findAll(Sort.by("description"));
		return productRepository.findAllByOrderByDescription();
	}
	
	public Product findById(Long id) throws Exception {
		return productRepository.findById(id)
			.orElseThrow(() -> new Exception(Messages.format("product.not.found", id)));
	}
	
	public Product findByGtin(String gtin) throws Exception {
		return productRepository.findByGtin(gtin)
			.orElseThrow(() -> new Exception(Messages.format("product.not.found", gtin)));
	}
	
	public Product insert(Product product) throws Exception {
		product.setDescription(product.getDescription().toUpperCase());
		String gtin = product.getGtin();
		if (gtin != null && !gtin.isEmpty()) {
			Product p = productRepository.findByGtin(gtin).orElse(null);
			if (p != null)
				throw new Exception(Messages.format("product.gtin.duplicate", gtin));
		}
		return productRepository.save(product);
	}
	
	public Product update(Product product) throws Exception {
		product.setDescription(product.getDescription().toUpperCase());
		String gtin = product.getGtin();
		if (gtin != null && !gtin.isEmpty()) {
			Product p = productRepository.findByGtin(gtin).orElse(null);
			if (p != null && p.getId() != product.getId())
				throw new Exception(Messages.format("product.gtin.duplicate", gtin));
		}
		return productRepository.save(product);
	}

	public void delete(Long id) throws Exception {
		productRepository.delete(findById(id));
	}
	
	@Transactional
	public void incQuantity(Long id, Long increment) {
		System.out.print("\nproductRepository.incQuantity: " + productRepository.incQuantity(id, increment) + "\n");
	}
	
}
