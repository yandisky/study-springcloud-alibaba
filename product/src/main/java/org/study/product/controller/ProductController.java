package org.study.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.study.bean.bean.Product;
import org.study.product.service.ProductService;
import org.study.utils.constants.HttpCode;
import org.study.utils.resp.Result;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/get/{pid}")
    public Product getProduct(@PathVariable("pid") Long pid) {
        return productService.getProductById(pid);
    }

    @GetMapping(value = "/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count) {
        int updateCount = productService.updateProductStockById(count, pid);
        return new Result<>(HttpCode.SUCCESS, "成功", updateCount);
    }
}
