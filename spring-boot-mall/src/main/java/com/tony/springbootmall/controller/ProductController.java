package com.tony.springbootmall.controller;

import com.tony.springbootmall.constant.ProductCategory;
import com.tony.springbootmall.dto.ProductQueryParams;
import com.tony.springbootmall.dto.ProductRequest;
import com.tony.springbootmall.model.Product;
import com.tony.springbootmall.service.ProductService;
import com.tony.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//加這個註解max跟min才會生效
@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //查詢全部商品，也要計算商品總比數是多少
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //添加搜尋條件，使用商品分類去搜尋
            //在url取得請求參數，category類別
            //required = false是可有可無，url會變products=?
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //控制排序sorting
            //假如沒有傳orderBy參數過來，用created_date去查
            @RequestParam (defaultValue = "created_date") String orderBy,
            //sort決定用小排到大，還是用大排到小
            @RequestParam (defaultValue = "desc") String sort,

            //分頁 Pagination(limit取得幾筆數據，offset跳過多少參數)
            //預設取得五筆參數(不跳過任何一筆數據)
            //用defaultValue(限制)筆數，對資料庫效能比較好(比如說用*就對效能不好)
            //Max,Min避免超過1000跟低於0
            @RequestParam (defaultValue = "5")@Max(1000) @Min(0) Integer limit,
            @RequestParam (defaultValue = "0")@Min(0) Integer offset
    ){
        //如果要增加搜尋條件只要在ProductQueryParams dto增加條件就好
        ProductQueryParams params = new ProductQueryParams();
        params.setCategory(category);
        params.setSearch(search);
        params.setOrderBy(orderBy);
        params.setSort(sort);
        params.setLimit(limit);
        params.setOffset(offset);

        //並沒有判斷List有沒有物件
        //因為RESTFUL API設計理念不管裡面有沒有東西，
        List<Product> productList = productService.getProducts(params);

        //取得product，商品總數
        Integer total = productService.countProducts(params);//之所以參數放params，是因為會根據查詢條件不同而改變

        //分頁(設定responseBody的值)
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResult(productList);


        return  ResponseEntity.status(HttpStatus.OK).body(page);

    }

    //查詢單一商品
    //當前端取得這個路徑，這個方法就會去service拿productId
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        //product如果是null，404，
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }


    //新增商品
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {

        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    //修改商品
    @PutMapping("products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        //檢查product是否存在
        Product product = productService.getProductById(productId);//取得商品數據

        //如果商品是null，要給前端404
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改商品數據
        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    //刪除商品(只要確定商品消失就直接回傳，不用回傳404)
    //商品存在，成功的刪除/商品本來就不存在
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {

        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //204 NoContent已刪除內容
    }

}
