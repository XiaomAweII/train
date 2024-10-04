package com.xiaoweii.train.member.controller;


import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.member.req.MemberLoginReq;
import com.xiaoweii.train.member.req.MemberRegisterReq;
import com.xiaoweii.train.member.req.MemberSendCodeReq;
import com.xiaoweii.train.member.resp.MemberLoginResp;
import com.xiaoweii.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    //    @Resource
    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Long> count() {
        long count = memberService.count();
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq req) {//添加注解@RequestBody,即接收方式以json字符串的方式接收
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> sendCode(@Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
