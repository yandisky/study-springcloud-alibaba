package org.study.bean.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.study.utils.id.SnowFlakeFactory;
import org.study.utils.psswd.PasswordUtils;

import java.io.Serializable;

@Data
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 4611168375036069967L;

    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    @TableField("t_username")
    private String username;

    @TableField("t_password")
    private String password;

    @TableField("t_phone")
    private String phone;

    @TableField("t_address")
    private String address;

    public User() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
        //默认密码
        this.password = PasswordUtils.getPassword("123456");
    }
}
