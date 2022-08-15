package com.example.spring.boot.sales.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.boot.sales.Messages;
import com.example.spring.boot.sales.dto.FullSaleDTO;
import com.example.spring.boot.sales.dto.SaleCustomerDTO;
import com.example.spring.boot.sales.dto.SaleDTO;
import com.example.spring.boot.sales.dto.SaleFilterDTO;
import com.example.spring.boot.sales.dto.SaleItemDTO;
import com.example.spring.boot.sales.dto.SaleProductDTO;
import com.example.spring.boot.sales.dto.SaleSellerDTO;
import com.example.spring.boot.sales.entities.Person;
import com.example.spring.boot.sales.entities.Product;
import com.example.spring.boot.sales.entities.Sale;
import com.example.spring.boot.sales.entities.SaleItem;
import com.example.spring.boot.sales.repositories.SaleItemRepository;
import com.example.spring.boot.sales.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private SaleItemRepository saleItemRepository;
	
	private SaleSellerDTO toSellerDTO(Person person) {
		return person == null ? null : new SaleSellerDTO(
			person.getId(),
			person.getName());
	}
	
	private SaleCustomerDTO toCustomerDTO(Person person) {
		return person == null ? null : new SaleCustomerDTO(
			person.getId(),
			person.getName(),
			person.getTaxId());
	}
	
	private SaleDTO toSaleDTO(Sale sale) {
		return sale == null ? null : new SaleDTO(
			sale.getId(), 
			sale.getDate(), 
			toSellerDTO(sale.getSeller()), 
			toCustomerDTO(sale.getCustomer()), 
			sale.getComment());
	}
	
	private SaleProductDTO toSaleProdutctDTO(Product product) {
		return new SaleProductDTO(
			product.getId(),
			product.getGtin(),
			product.getDescription());
	}
	
	private SaleItemDTO toSaleItemDTO(SaleItem saleItem) {
		return new SaleItemDTO(
			saleItem.getId(),
			toSaleProdutctDTO(saleItem.getProduct()),
			saleItem.getQuantity(),
			saleItem.getCostPrice());
	}

	public List<SaleDTO> findAll() {
		List<SaleDTO> result = new ArrayList<>();
		saleRepository.findAll().forEach(sale -> {
			result.add(toSaleDTO(sale));
		});
		return result;
	}

	public List<SaleDTO> findByFilter(SaleFilterDTO filter) {
		List<SaleDTO> result = new ArrayList<>();
		saleRepository.findByFilter(
				filter.getSellerId(),
				filter.getCustomerId(),
				filter.getStartDate(),
				filter.getEndDate()
			).forEach(sale -> {
				result.add(toSaleDTO(sale));
			});
		return result;
	}

	private List<SaleItemDTO> findSaleItems(Long saleId) {
		List<SaleItemDTO> result = new ArrayList<>();
		saleItemRepository.findBySaleId(saleId).forEach(item -> {
			result.add(toSaleItemDTO(item));
		});
		return result;
	}
	
	public FullSaleDTO findById(Long id) throws Exception {
		Sale sale = saleRepository.findById(id)
			.orElseThrow(() -> new Exception(Messages.format("sale.not.found", id)));
		
		FullSaleDTO fullSaleDTO = new FullSaleDTO();
		fullSaleDTO.setId(sale.getId());
		fullSaleDTO.setDate(sale.getDate());
		fullSaleDTO.setSeller(toSellerDTO(sale.getSeller()));
		fullSaleDTO.setCustomer(toCustomerDTO(sale.getCustomer()));
		fullSaleDTO.setComment(sale.getComment());
		fullSaleDTO.setItems(findSaleItems(sale.getId()));
			
		return fullSaleDTO;
	}

	
	@Transactional
	public Sale insert(Sale sale) {
		sale.setDate(LocalDate.now());
		System.out.print("\n\n" + sale + "\n\n");
		saleRepository.save(sale);
		return sale;
	}
	
	public Sale update(Sale sale) {
		return saleRepository.save(sale);
	}

	public void delete(Long id) throws Exception {
		saleRepository.deleteById(findById(id).getId());
	}
	
}
