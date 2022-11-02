package org.study.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.study.bean.bean.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
