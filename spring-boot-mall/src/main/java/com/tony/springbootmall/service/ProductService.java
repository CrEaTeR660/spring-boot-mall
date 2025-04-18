package com.tony.springbootmall.service;

import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;

public interface
ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    //因為沒有回傳值給void
    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);

}
