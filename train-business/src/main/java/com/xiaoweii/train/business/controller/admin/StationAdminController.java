package com.xiaoweii.train.business.controller.admin;


import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.business.req.StationQueryReq;
import com.xiaoweii.train.business.req.StationSaveReq;
import com.xiaoweii.train.business.resp.StationQueryResp;
import com.xiaoweii.train.business.service.StationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/station")
public class StationAdminController {

    @Resource
    private StationService stationService;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody StationSaveReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        stationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        PageResp<StationQueryResp> list = stationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {//使用@PathVariable注解,将id与restful格式的路径{id}关联起来
        stationService.delete(id);
        return new CommonResp<>();
    }


}
