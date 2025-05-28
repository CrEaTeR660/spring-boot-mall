package com.tony.springbootmall.rowmapper;

import com.tony.springbootmall.constant.ProductCategory;
import com.tony.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//把 ResultSet → Java Bean（VO/DTO）
//將資料庫查詢結果一列，轉成一個 Java 物件

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i ) throws SQLException {

        Product product = new Product();

        //設定product對象，把要拿到的結果跟對應column的名稱一樣
        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));

        String category = resultSet.getString("category"); //接住從資料庫出來的值
        ProductCategory productCategory = ProductCategory.valueOf(category);//把字串轉枚舉類型
        product.setCategory(productCategory);

//        product.setCategory(productCategory.valueOf(resultSet.getString("category")));

        product.setImageUrl(resultSet.getString("image_url"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreateDate(resultSet.getObject("created_date", LocalDateTime.class));
        product.setCreateDate(resultSet.getObject("last_modified_date", LocalDateTime.class));
        return product;
    }
}
