package com.xiaoweii.train.business.controller.admin;


import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.business.req.TrainSeatQueryReq;
import com.xiaoweii.train.business.req.TrainSeatSaveReq;
import com.xiaoweii.train.business.resp.TrainSeatQueryResp;
import com.xiaoweii.train.business.service.TrainSeatService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-seat")
public class TrainSeatAdminController {

    @Resource
    private TrainSeatService trainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody TrainSeatSaveReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        trainSeatService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainSeatQueryResp>> queryList(@Valid TrainSeatQueryReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        PageResp<TrainSeatQueryResp> list = trainSeatService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {//使用@PathVariable注解,将id与restful格式的路径{id}关联起来
        trainSeatService.delete(id);
        return new CommonResp<>();
    }


}
