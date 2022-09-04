package com.yequ.common.domain.service.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yequ.common.base.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-03
 **/
@Data
@TableName("sys_user_t")
public class UserEntity extends BaseEntity {

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 0:男，1：女
     */
    private int sex;

    /**
     * 工号
     */
    private  String workId;

    /**
     * 邮箱
     */
    private  String email;

}
