package com.longifly.api.entity;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import lombok.Data;

/**
 * @author WT
 * @date 2020/08/25
 */
@Data
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String nickname;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private Integer departmentId;

}
