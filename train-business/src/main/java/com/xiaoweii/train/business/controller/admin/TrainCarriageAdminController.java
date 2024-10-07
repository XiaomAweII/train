package com.xiaoweii.train.business.controller.admin;


import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.business.req.TrainCarriageQueryReq;
import com.xiaoweii.train.business.req.TrainCarriageSaveReq;
import com.xiaoweii.train.business.resp.TrainCarriageQueryResp;
import com.xiaoweii.train.business.service.TrainCarriageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-carriage")
public class TrainCarriageAdminController {

    @Resource
    private TrainCarriageService trainCarriageService;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody TrainCarriageSaveReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        trainCarriageService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainCarriageQueryResp>> queryList(@Valid TrainCarriageQueryReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        PageResp<TrainCarriageQueryResp> list = trainCarriageService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {//使用@PathVariable注解,将id与restful格式的路径{id}关联起来
        trainCarriageService.delete(id);
        return new CommonResp<>();
    }


}
