package org.study.order.feign.fallback.factory;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.study.bean.bean.Product;
import org.study.order.feign.ProductService;
import org.study.utils.resp.Result;

@Component
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product getProduct(Long pid) {
                Product product = new Product();
                product.setId(-1L);
                return product;
            }

            @Override
            public Result<Integer> updateCount(Long pid, Integer count) {
                Result<Integer> result = new Result<>();
                result.setCode(1001);
                result.setCodeMsg("fallback test");
                return result;
            }
        };
    }
}
