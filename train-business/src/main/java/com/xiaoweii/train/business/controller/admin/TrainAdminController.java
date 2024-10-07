package com.xiaoweii.train.business.controller.admin;


import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.business.req.TrainQueryReq;
import com.xiaoweii.train.business.req.TrainSaveReq;
import com.xiaoweii.train.business.resp.TrainQueryResp;
import com.xiaoweii.train.business.service.TrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train")
public class TrainAdminController {

    @Resource
    private TrainService trainService;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody TrainSaveReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        trainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainQueryResp>> queryList(@Valid TrainQueryReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        PageResp<TrainQueryResp> list = trainService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {//使用@PathVariable注解,将id与restful格式的路径{id}关联起来
        trainService.delete(id);
        return new CommonResp<>();
    }

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryList() {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        List<TrainQueryResp> list = trainService.queryAll();
        return new CommonResp<>(list);
    }


}
