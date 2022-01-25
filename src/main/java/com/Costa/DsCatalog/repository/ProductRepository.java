package com.Costa.DsCatalog.repository;

import com.Costa.DsCatalog.entity.Category;
import com.Costa.DsCatalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

}
