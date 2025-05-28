package com.tony.springbootmall.dao;

import com.tony.springbootmall.dto.ProductQueryParams;
import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

     //查詢(設定查詢條件)
     List<Product> getProducts(ProductQueryParams params);

     //計算商品總數
     Integer countProducts(ProductQueryParams params);

     Product getProductById(Integer productId);

     Integer createProduct(ProductRequest productRequest);

     void updateProduct(Integer productId, ProductRequest productRequest);

     void deleteProduct(Integer productId);

}
