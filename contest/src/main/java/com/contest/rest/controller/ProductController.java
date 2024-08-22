package com.contest.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.ProductDTO;
import com.contest.rest.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/product")
public class ProductController {
	/* 
	Create : 데이터 생성(POST)
	Read : 데이터 조회(GET)
	Update : 데이터 수정(PUT, PATCH)
	Delete : 데이터 삭제(DELETE)
	*/
	
	@Autowired
	private ProductService pservice;
	
	// 새 상품 추가
	@PostMapping()
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO product) {
		ProductDTO checkProduct = pservice.getProduct(product.getPName());
		
		// 등록되지 않은 product라면
		if(checkProduct == null) {
			pservice.addProduct(product);
			System.out.println("상품 등록 완료 : "+product);
			return ResponseEntity.status(200).body(product);
		}
		// 이미 등록된 product라면
		else {
			System.out.println("이미 등록된 상품입니다. : " + product);
			return ResponseEntity.status(200).body(product);
		}
	}
	
	// 전체 상품 조회
	@GetMapping()
	public ResponseEntity<?> getProducts() {
		List<ProductDTO> pList = pservice.getProducts();
		return ResponseEntity.status(200).body(pList);
	}
	
	// 특정 상품 조회
	@GetMapping("/{pName}")
	public ResponseEntity<?> getProduct(@PathVariable String pName) {
		ProductDTO product = pservice.getProduct(pName);
		
		if(product == null) {
			return ResponseEntity.badRequest().body("등록된 "+pName+" 가(이) 없습니다.");
		}
		return ResponseEntity.status(200).body(product);
	}
	
	// 특정 상품 수정
	@PutMapping("/{oldpName}")
	public ResponseEntity<?> updateProduct(@PathVariable String oldpName, @RequestBody ProductDTO product) {
		ProductDTO checkProduct = pservice.getProduct(oldpName);
		
		if(checkProduct == null) {
			return ResponseEntity.badRequest().body("등록된 "+oldpName+" 가(이) 없습니다.");
		}
		else {
			pservice.updateProduct(oldpName, product);
			return ResponseEntity.ok("업데이트 됨.");
		}
	}
	
	// 특정 상품 삭제
	@DeleteMapping("/{pName}")
	public ResponseEntity<?> deleteProduct(@PathVariable String pName) {
		ProductDTO checkProduct = pservice.getProduct(pName);
		
		if(checkProduct == null) {
			return ResponseEntity.badRequest().body("등록된 "+pName+" 가(이) 없습니다.");
		}
		else {
			pservice.deleteProduct(pName);
			return ResponseEntity.ok("삭제됨.");
		}
	}
}
