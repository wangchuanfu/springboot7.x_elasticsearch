package com.j1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangchuanfu on 20/7/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    //会员id
    private Integer memberId;
    //会员账号
    private String account;
    //邮件
    private String email;
    //手机
    private String mobile;
    //登陆密码
    private  String loginPassword;
    private  String address;

}
