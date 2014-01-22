package com.myProducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProducts.model.Product;


/**
 * Author: anwar kharrat
 * Blog: http://anwarkharrat.wordpress.com/
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
