package org.study.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.study.bean.bean.OrderItem;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
