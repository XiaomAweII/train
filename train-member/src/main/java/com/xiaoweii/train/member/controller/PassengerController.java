package com.xiaoweii.train.member.controller;


import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.member.req.PassengerQueryReq;
import com.xiaoweii.train.member.req.PassengerSaveReq;
import com.xiaoweii.train.member.resp.PassengerQueryResp;
import com.xiaoweii.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody PassengerSaveReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        req.setMemberId(LoginMemberContext.getId());//在这里进行获取memberId, 让代码更通用, 当然也可以放在Service层中统一处理, 不过后期还需要控台即能够看到某个会员的乘客, 也要能查看所有乘客
        PageResp<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }

}
