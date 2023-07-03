package com.inditext.zara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditext.zara.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

