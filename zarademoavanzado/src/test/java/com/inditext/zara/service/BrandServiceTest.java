package com.inditext.zara.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditext.zara.exception.ResourceNotFoundException;
import com.inditext.zara.model.Brand;
import com.inditext.zara.repository.BrandRepository;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @Test
    public void testGetAllBrands() {
        // Create some dummy brands
        Brand brand1 = new Brand(1L, "Brand 1");
        Brand brand2 = new Brand(2L, "Brand 2");
        List<Brand> brands = Arrays.asList(brand1, brand2);

        // Mock the repository method
        when(brandRepository.findAll()).thenReturn(brands);

        // Call the service method
        List<Brand> result = brandService.getAllBrands();

        // Verify the result
        assertEquals(brands, result);
        verify(brandRepository).findAll();
    }

    @Test
    public void testGetBrandById() {
        // Create a dummy brand
        Brand brand = new Brand(1L, "Brand 1");

        // Mock the repository method
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

        // Call the service method
        Brand result = brandService.getBrandById(1L);

        // Verify the result
        assertEquals(brand, result);
        verify(brandRepository).findById(1L);
    }

    @Test
    public void testGetBrandByIdNotFound() {
        // Mock the repository method
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and verify that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> brandService.getBrandById(1L));
        verify(brandRepository).findById(1L);
    }

    @Test
    public void testCreateBrand() {
        // Create a dummy brand
        Brand brand = new Brand(1L, "Brand 1");

        // Mock the repository method
        when(brandRepository.save(brand)).thenReturn(brand);

        // Call the service method
        Brand result = brandService.createBrand(brand);

        // Verify the result
        assertEquals(brand, result);
        verify(brandRepository).save(brand);
    }

    @Test
    public void testUpdateBrand() {
        // Create a dummy brand
        Brand brand = new Brand(1L, "Brand 1");

        // Mock the repository method
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.save(brand)).thenReturn(brand);

        // Call the service method
        Brand updatedBrand = new Brand(1L, "Updated Brand 1");
        Brand result = brandService.updateBrand(1L, updatedBrand);

        // Verify the result
        assertEquals(result.getName(), "Updated Brand 1");
        verify(brandRepository).findById(1L);
        verify(brandRepository).save(brand);
    }

    @Test
    public void testDeleteBrand() {
        // Create a dummy brand
        Brand brand = new Brand(1L, "Brand 1");

        // Mock the repository method
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        doNothing().when(brandRepository).deleteById(1L);

        // Call the service method
        brandService.deleteBrand(1L);

        // Verify that the repository delete method is called
        verify(brandRepository).findById(1L);
        verify(brandRepository).deleteById(1L);
    }

    @Test
    public void testDeleteBrandNotFound() {
        // Mock the repository method
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and verify that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> brandService.deleteBrand(1L));
        verify(brandRepository).findById(1L);
        verify(brandRepository, never()).delete(any(Brand.class));
    }
}
