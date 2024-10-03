package com.xiaoweii.train.member.controller;


import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.member.req.MemberRegisterReq;
import com.xiaoweii.train.member.service.MemberService;
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
    public CommonResp<Long> register(MemberRegisterReq req) {
        long register = memberService.register(req);
//        CommonResp<Long> commonResp = new CommonResp<>();
//        commonResp.setContent(register);
//        return commonResp;
        //简写
        return new CommonResp<>(register);
    }
}
