package com.contest.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.rest.domain.dto.ProductDTO;
import com.contest.rest.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper pmapper;

	// 새 상품 추가
	@Override
	public boolean addProduct(ProductDTO product) {
		return pmapper.insertProduct(product) == 1;
	}

	// 특정 상품 조회
	@Override
	public ProductDTO getProduct(String pName) {
		System.out.println("PSimpl 에서 p_name : " + pName);
		System.out.println("pmapper.getProductByName(p_name) 결과 : " + pmapper.getProductByName(pName));
		return pmapper.getProductByName(pName);
	}
	
	// 전체 상품 조회
	@Override
	public List<ProductDTO> getProducts() {
		return pmapper.getProducts();
	}

	// 특정 상품 수정
	@Override
	public boolean updateProduct(String oldpName, ProductDTO product) {
		return pmapper.updateProduct(oldpName, product) == 1;
	}

	// 특정 상품 삭제
	@Override
	public boolean deleteProduct(String pName) {
		return pmapper.deleteProduct(pName) == 1;
	}

}
