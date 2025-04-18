package com.tony.springbootmall.service;

import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;

import java.util.List;

public interface
ProductService {

    //查詢全部
    List<Product> getProducts();

    //單一查詢
    Product getProductById(Integer productId);
    //新增商品
    Integer createProduct(ProductRequest productRequest);

    //更新商品，因為沒有回傳值給void
    void updateProduct(Integer productId, ProductRequest productRequest);
    //刪除商品
    void deleteProduct(Integer productId);

}
