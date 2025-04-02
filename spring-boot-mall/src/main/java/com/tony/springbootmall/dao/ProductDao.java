package com.tony.springbootmall.dao;

import com.tony.springbootmall.model.Product;

public interface ProductDao {

     Product getProductById(Integer productId);
}
