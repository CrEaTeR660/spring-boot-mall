package com.tony.springbootmall.model;

import com.tony.springbootmall.constant.ProductCategory;

import java.util.Date;

public class Product {
    //商品ID
    private Integer productId;
    //商品名字
    private String productName;
    //商品分類，已預設好類型
    private ProductCategory category;
    //商品圖片網址
    private String ImageUrl;
    //商品價格
    private Integer price;
    //商品庫存
    private Integer stock;
    //商品描述
    private String description;

    //商品創建時間
    //Date類型是對照格林威治的時間
    private Date createDate;
    //商品最後修改時間
    private Date lastModifiedDate;


    public Product() {
    }


    public Product(Integer productId, String productName, ProductCategory category, String imageUrl, Integer price, Integer stock, String description, Date createDate, Date lastModifiedDate) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        ImageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.createDate = createDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
