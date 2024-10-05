package com.xiaoweii.train.common.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResp {
    private Long id;

    private String mobile;

    private String token;

    //换一种新的方式生成toString,QAQ,没什么用随便试试
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberLoginResp{");
        sb.append("id=").append(id);
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}