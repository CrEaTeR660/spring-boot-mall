package com.tony.springbootmall.dto;

import com.tony.springbootmall.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.util.Date;

public class ProductRequest {


    //商品名字
    @NotNull
    private String productName;

    //商品分類，已預設好類型
    @NotNull
    private ProductCategory category;

    @NotNull
    //商品圖片網址
    private String ImageUrl;

    @NotNull
    //商品價格
    private Integer price;

    @NotNull
    //商品庫存
    private Integer stock;

    //商品描述
    private String description;

    public ProductRequest() {
    }

    public ProductRequest(String productName, ProductCategory category, String imageUrl, Integer price, Integer stock, String description) {
        this.productName = productName;
        this.category = category;
        ImageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
