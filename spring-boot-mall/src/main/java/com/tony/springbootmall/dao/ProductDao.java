package com.tony.springbootmall.dao;

import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;

public interface ProductDao {

     Product getProductById(Integer productId);

     Integer createProduct(ProductRequest productRequest);

     void updateProduct(Integer productId, ProductRequest productRequest);

     void deleteProduct(Integer productId);

}
