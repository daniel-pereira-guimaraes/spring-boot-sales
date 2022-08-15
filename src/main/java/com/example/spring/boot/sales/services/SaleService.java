package com.example.spring.boot.sales.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.boot.sales.Messages;
import com.example.spring.boot.sales.dto.SaleFilterDTO;
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
	
	public List<Sale> findAll() {
		return saleRepository.findAll();
	}
	
	public Sale findById(Long id) throws Exception {
		return saleRepository.findById(id)
			.orElseThrow(() -> new Exception(Messages.format("sale.not.found", id)));
	}

	public List<Sale> findByFilter(SaleFilterDTO filter) {
		return saleRepository.findByFilter(
			filter.getSellerId(),
			filter.getCustomerId(),
			filter.getStartDate(),
			filter.getEndDate());
	}

	
	@Transactional
	public Sale insert(Sale sale) {
		sale.setDate(LocalDate.now());
		return saleRepository.save(sale);
	}
	
	@Transactional
	public Sale update(Sale sale) {
		return saleRepository.save(sale);
	}

	@Transactional
	public void delete(Long id) throws Exception {
		saleRepository.deleteById(findById(id).getId());
	}
	
	public List<SaleItem> findItemBySaleId(Long saleId) {
		return saleItemRepository.findBySaleId(saleId);
	}
	
	public SaleItem findItemById(Long id) throws Exception {
		return saleItemRepository.findById(id)
			.orElseThrow(() -> new Exception(Messages.format("sale.item.not.found", id)));
	}

	@Transactional
	public SaleItem insertItem(SaleItem saleItem) throws Exception {
		return saleItemRepository.save(saleItem);
	}
	
	@Transactional
	public SaleItem updateItem(SaleItem item) {
		return saleItemRepository.save(item);
	}

	@Transactional
	public void deleteItem(Long id) throws Exception {
		saleItemRepository.deleteById(findItemById(id).getId());
	}
	
}
