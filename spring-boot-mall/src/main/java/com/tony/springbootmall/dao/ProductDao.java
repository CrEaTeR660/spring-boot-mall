package com.tony.springbootmall.dao;

import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {


     List<Product> getProducts();

     Product getProductById(Integer productId);

     Integer createProduct(ProductRequest productRequest);

     void updateProduct(Integer productId, ProductRequest productRequest);

     void deleteProduct(Integer productId);

}
