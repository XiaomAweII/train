package com.xiaoweii.train.business.controller.admin;


import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.business.req.TrainStationQueryReq;
import com.xiaoweii.train.business.req.TrainStationSaveReq;
import com.xiaoweii.train.business.resp.TrainStationQueryResp;
import com.xiaoweii.train.business.service.TrainStationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-station")
public class TrainStationAdminController {

    @Resource
    private TrainStationService trainStationService;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody TrainStationSaveReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        trainStationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainStationQueryResp>> queryList(@Valid TrainStationQueryReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        PageResp<TrainStationQueryResp> list = trainStationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {//使用@PathVariable注解,将id与restful格式的路径{id}关联起来
        trainStationService.delete(id);
        return new CommonResp<>();
    }


}
