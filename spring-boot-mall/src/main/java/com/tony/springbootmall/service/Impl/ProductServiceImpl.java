package com.tony.springbootmall.service.Impl;

import com.tony.springbootmall.dao.ProductDao;
import com.tony.springbootmall.dto.ProductQueryParams;
import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;
import com.tony.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    //取得商品類型
    @Override
    public List<Product> getProducts(ProductQueryParams params) {

        return productDao.getProducts(params);
    }

    //算商品總數
    @Override
    public Integer countProducts(ProductQueryParams params) {
        return productDao.countProducts(params);
    }

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {


        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }


}
