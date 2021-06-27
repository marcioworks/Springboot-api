package com.marcio.springbootapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query("SELECT DISTINCT  obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
  Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories,
      Pageable pageRequest);

}
