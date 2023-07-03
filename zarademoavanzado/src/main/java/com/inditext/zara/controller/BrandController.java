package com.inditext.zara.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditext.zara.model.Brand;
import com.inditext.zara.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {
	private final BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping
	public List<Brand> getAllBrands() {
		return brandService.getAllBrands();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
		Brand brand = brandService.getBrandById(id);
		return ResponseEntity.ok(brand);
	}

	@PostMapping
	public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
		Brand createdBrand = brandService.createBrand(brand);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand updatedBrand) {
		Brand brand = brandService.updateBrand(id, updatedBrand);
		return ResponseEntity.ok(brand);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
		brandService.deleteBrand(id);
		return ResponseEntity.noContent().build();
	}
}
