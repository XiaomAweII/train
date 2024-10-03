package com.xiaoweii.train.member.req;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterReq {

    @NotBlank(message = "[手机号] 不能为空")//使得mobile不能为空, 并增加提示
    private String mobile;

}
