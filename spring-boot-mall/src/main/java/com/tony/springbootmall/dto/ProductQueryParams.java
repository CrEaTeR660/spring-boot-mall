package com.tony.springbootmall.dto;

import com.tony.springbootmall.constant.ProductCategory;

//類別:是為了以後如果要再增加查詢條件動這便就好，不用再去動service或dao層
//參數值直接在這裡加就好
public class ProductQueryParams {

    private ProductCategory category;
    private String search;
    // 未來可加：
    // private Integer priceMin;
    // private Integer priceMax;

    private String orderBy;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    //由大到小搜尋還是小到大
    private String sort;

    //頁數
    private Integer limit;
    private Integer offset;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }


}
