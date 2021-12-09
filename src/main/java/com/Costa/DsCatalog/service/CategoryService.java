package com.Costa.DsCatalog.service;

import com.Costa.DsCatalog.controller.CategoryController;
import com.Costa.DsCatalog.dto.CategoryDTO;
import com.Costa.DsCatalog.entity.Category;
import com.Costa.DsCatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
