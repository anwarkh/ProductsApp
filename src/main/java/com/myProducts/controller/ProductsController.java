package com.myProducts.controller;

import com.myProducts.model.Product;
import com.myProducts.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: anwar kharrat 
 * 
 * Blog: http://anwarkharrat.wordpress.com/
 */
@Controller
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Product createProduct(@RequestBody Product smartphone) {
		return productService.create(smartphone);
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Product editProduct(@PathVariable int id,
			@RequestBody Product product) {
		product.setId(id);
		return productService.update(product);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Product deleteProduct(@PathVariable int id) {
		return productService.delete(id);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Product> allProducts() {
		return productService.getAll();
	}
}