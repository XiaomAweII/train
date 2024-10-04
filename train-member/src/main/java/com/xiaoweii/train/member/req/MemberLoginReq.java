package com.xiaoweii.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * 发送验证码
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberLoginReq {

    @NotBlank(message = "[手机号] 不能为空")//使得mobile不能为空, 并增加提示
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")//正则表达式, 手机号码格式
    private String mobile;

    @NotBlank(message = "[短信验证码] 不能为空")//使得mobile不能为空, 并增加提示
    private String code;
}
