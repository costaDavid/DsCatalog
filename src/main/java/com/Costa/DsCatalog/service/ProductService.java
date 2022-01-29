package com.Costa.DsCatalog.service;

import com.Costa.DsCatalog.dto.CategoryDTO;
import com.Costa.DsCatalog.dto.ProductDTO;
import com.Costa.DsCatalog.entity.Category;
import com.Costa.DsCatalog.entity.Product;
import com.Costa.DsCatalog.repository.CategoryRepository;
import com.Costa.DsCatalog.repository.ProductRepository;
import com.Costa.DsCatalog.service.exceptions.DataBaseException;
import com.Costa.DsCatalog.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = productRepository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional(readOnly = true)
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product entity = new Product();
        // copyDtoToEntity(productDTO, entity);
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setPrice(productDTO.getPrice());
        entity.setDate(productDTO.getDate());
        entity.setImgUrl(productDTO.getImgUrl());
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional(readOnly = true)
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        try {
            Product entity = productRepository.getOne(id);
            //     copyDtoToEntity(productDTO, entity);
            entity.setName(productDTO.getName());
            entity.setDescription(productDTO.getDescription());
            entity.setPrice(productDTO.getPrice());
            entity.setDate(productDTO.getDate());
            entity.setImgUrl(productDTO.getImgUrl());
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrety violation");
        }
    }
}

//    private void copyDtoToEntity (ProductDTO productDTO, Product entity){
//        entity.setName(productDTO.getName());
//        entity.setDescription(productDTO.getDescription());
//        entity.setPrice(productDTO.getPrice());
//        entity.setDate(productDTO.getDate());
//        entity.setImgUrl(productDTO.getImgUrl());
//
//        entity.getCategories().clear();
//        for (CategoryDTO categoryDTO : productDTO.getCategories()){
//            Category category = categoryRepository.getOne(categoryDTO.getId());
//            entity.getCategories().add(category);
//        }
//    }
//}