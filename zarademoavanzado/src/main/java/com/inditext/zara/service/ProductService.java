package com.inditext.zara.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inditext.zara.exception.ResourceNotFoundException;
import com.inditext.zara.model.Product;
import com.inditext.zara.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
    	Optional<Product> productDB = productRepository.findById(id);
    	if (productDB.isPresent()) {
			return productDB.get();
		}
		throw new ResourceNotFoundException("Product not found for id:" + id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product dbProduct = getProductById(id);
        dbProduct.setName(updatedProduct.getName());
        return productRepository.save(dbProduct);
    }

    public void deleteProduct(Long id) {
    	getProductById(id);
        productRepository.deleteById(id);
    }
}

