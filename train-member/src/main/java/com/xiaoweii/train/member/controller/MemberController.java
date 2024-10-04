package com.xiaoweii.train.member.controller;


import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.member.req.MemberRegisterReq;
import com.xiaoweii.train.member.req.MemberSendCodeReq;
import com.xiaoweii.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResp<Long> sendCode(@Valid MemberSendCodeReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        memberService.sendCode(req);
        return new CommonResp<>();
    }
}
