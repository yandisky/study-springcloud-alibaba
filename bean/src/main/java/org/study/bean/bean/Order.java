package org.study.bean.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.study.utils.id.SnowFlakeFactory;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("t_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 8317498428208967909L;
    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 用户id
     */
    @TableField("t_user_id")
    private Long userId;

    /**
     * 用户名
     */
    @TableField("t_user_name")
    private String username;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;


    /**
     * 商品价格（总价）
     */
    @TableField("t_total_price")
    private BigDecimal totalPrice;

    public Order() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
    }
}
