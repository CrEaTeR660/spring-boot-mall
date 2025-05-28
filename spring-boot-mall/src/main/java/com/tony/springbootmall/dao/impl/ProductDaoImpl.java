package com.tony.springbootmall.dao.impl;

import com.tony.springbootmall.constant.ProductCategory;
import com.tony.springbootmall.dao.ProductDao;
import com.tony.springbootmall.dto.ProductQueryParams;
import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;
import com.tony.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    //查詢全部商品  ProductCategory(設定查詢條件)
    @Override
    public List<Product> getProducts(ProductQueryParams params) {
        //where 1= 1 最主要的理由是要自由的拼接category的值
        //如果是空值1=1也不會影響
        String sql = "select product_id,product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                " from product "
                + " where 1 = 1 ";

        Map<String, Object> map = new HashMap<>();
        //實作查詢條件
        //假如類別裡不是空值，加進去map裡，因加上where1=1
        //所以我之後如果加上and...就可以變動態的
        //假如前端的URL參數/products?category= FOOD，就會被丟進來
        if (params.getCategory() != null) {
            sql = sql + " AND category = :category ";
            map.put("category", params.getCategory().name()); //因為這類型是enum類型所以要轉型toString

        }
        if (params.getSearch() != null) {
            sql = sql + " AND product_name Like :search ";
            map.put("search", "%" + params.getSearch() + "%");
        }

        //排序
        //在後面直接拼接查詢條件，不用加判斷是否為null，是因為Controller層已經給defaultValue
        sql = sql + " ORDER BY " + params.getOrderBy() + " " + params.getSort();

        //分頁
        sql = sql + " limit :limit OFFSET :offset ";
        map.put("limit", params.getLimit());
        map.put("offset", params.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {

        String sql = " select product_id,product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product where product_id = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, description, created_date,last_modified_date) " +
                "values (:productName, :category, :imageUrl, :price, :stock, :description, :created_date, :last_modified_date) ";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());


        LocalDateTime now = LocalDateTime.now();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }


    //起始時間不用動，只要動最齁修改時間就好
    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        String sql = "UPDATE product SET " +
                "product_name = :productName, " +
                "category = :category, " +
                "image_url = :imageUrl, " +
                "price = :price, " +
                "stock = :stock, " +
                "description = :description, " +
                "last_modified_date = :last_modified_date " +
                "WHERE product_id = :productId";


        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("last_modified_date", LocalDateTime.now());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

}
