package com.inditext.zara.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inditext.zara.exception.ResourceNotFoundException;
import com.inditext.zara.model.Brand;
import com.inditext.zara.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            return brandOptional.get();
        }
        throw new ResourceNotFoundException("Brand not found with id:" + id);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, Brand updatedBrand) {
        Brand brand = getBrandById(id);
        brand.setName(updatedBrand.getName());
        return brandRepository.save(brand);
    }

    public void deleteBrand(Long id) {
    	getBrandById(id);
        brandRepository.deleteById(id);
    }
}

