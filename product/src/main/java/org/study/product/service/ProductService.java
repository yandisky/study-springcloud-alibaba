package org.study.product.service;

import org.study.bean.bean.Product;

public interface ProductService {
    Product getProductById(Long pid);

    int updateProductStockById(Integer count, Long id);
}
