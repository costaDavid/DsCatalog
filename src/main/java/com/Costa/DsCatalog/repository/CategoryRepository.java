package com.Costa.DsCatalog.repository;

import com.Costa.DsCatalog.dto.CategoryDTO;
import com.Costa.DsCatalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//@EnableJpaRepositories
@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {

}
