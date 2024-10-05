package com.xiaoweii.train.member.req;

import com.xiaoweii.train.common.req.PageReq;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

//不止可以给新增使用, 也可以给编辑使用, 所以跟Passenger保持一致
public class PassengerQueryReq extends PageReq {


    //因为使用了线程本地变量获取memberID, 所以memberID就不需要校验了
    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PassengerQueryReq{");
        sb.append("memberId=").append(memberId);
        sb.append('}');
        return sb.toString();
    }
}