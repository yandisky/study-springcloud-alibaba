package org.study.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.study.bean.bean.Product;
import org.study.order.feign.fallback.ProductServiceFallBack;
import org.study.utils.resp.Result;

@FeignClient(value = "server-product", fallback = ProductServiceFallBack.class)
public interface ProductService {
    @GetMapping(value = "/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}
