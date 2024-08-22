package com.contest.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.ProductDTO;

@Mapper
public interface ProductMapper {
	// Create
	int insertProduct(ProductDTO product);
	// Read
	ProductDTO getProductByName(String pName);
	List<ProductDTO> getProducts();
	// Update
	int updateProduct(String oldpName, ProductDTO product);
	// Delete
	int deleteProduct(String pName);

}
