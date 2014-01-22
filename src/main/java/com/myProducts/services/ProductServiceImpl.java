package com.myProducts.services;

import com.myProducts.model.Product;
import com.myProducts.repository.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: anwar kharrat Blog: http://anwarkharrat.wordpress.com/
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
       return productRepository.save(product);
    }

    @Override
    public Product get(int id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = get(product.getId());
        if(productToUpdate != null){
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
             return productRepository.save(product);
        }
       return null;
    }

    @Override
    public Product delete(int id) {
        Product productToDelete = get(id);
        if(productToDelete != null){
          productRepository.delete(id);  
          return productToDelete;
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
