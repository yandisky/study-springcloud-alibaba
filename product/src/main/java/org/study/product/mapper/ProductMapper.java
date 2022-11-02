package org.study.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.study.bean.bean.Product;

public interface ProductMapper extends BaseMapper<Product> {
    int updateProductStockById(@Param("count") Integer count, @Param("id") Long id);
}
