package com.contest.rest.service;

import java.util.List;

import com.contest.rest.domain.dto.ProductDTO;

public interface ProductService {
	// 새 상품 추가
	boolean addProduct(ProductDTO product);
	// 특정 상품 조회
	ProductDTO getProduct(String pName);
	// 상품 전체 조회
	List<ProductDTO> getProducts();
	// 특정 상품 수정
	boolean updateProduct(String oldpName, ProductDTO product);
	// 특정 상품 삭제
	boolean deleteProduct(String pName);
}
