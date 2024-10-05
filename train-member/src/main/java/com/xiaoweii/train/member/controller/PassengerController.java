package com.xiaoweii.train.member.controller;


import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.member.req.PassengerSaveReq;
import com.xiaoweii.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

}
