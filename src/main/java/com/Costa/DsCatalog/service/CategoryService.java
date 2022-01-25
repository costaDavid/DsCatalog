package com.Costa.DsCatalog.service;

import com.Costa.DsCatalog.dto.CategoryDTO;
import com.Costa.DsCatalog.entity.Category;
import com.Costa.DsCatalog.repository.CategoryRepository;
import com.Costa.DsCatalog.service.exceptions.DataBaseException;
import com.Costa.DsCatalog.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
        Page<Category> list = categoryRepository.findAll(pageRequest);
        return list.map(x -> new CategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }

    @Transactional(readOnly = true)
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category entity = new Category();
        entity.setName(categoryDTO.getName());
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional(readOnly = true)
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category entity = categoryRepository.getOne(id);
        entity.setName(categoryDTO.getName());
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }

    public void deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
                throw new ResourceNotFoundException("Id not found" + id);
        }
        catch (DataIntegrityViolationException e){
                throw new DataBaseException("Integrety violation");
        }
    }
}