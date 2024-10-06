package com.xiaoweii.train.${module}.controller;


import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.CommonResp;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.${module}.req.${Domain}QueryReq;
import com.xiaoweii.train.${module}.req.${Domain}SaveReq;
import com.xiaoweii.train.${module}.resp.${Domain}QueryResp;
import com.xiaoweii.train.${module}.service.${Domain}Service;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${do_main}")
public class ${Domain}Controller {

    @Resource
    private ${Domain}Service ${domain}Service;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody ${Domain}SaveReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        ${domain}Service.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<${Domain}QueryResp>> queryList(@Valid ${Domain}QueryReq req) {//@Valid注解相当于一个开关, 加上之后, Validation才会生效
        req.setMemberId(LoginMemberContext.getId());//在这里进行获取${module}Id, 让代码更通用, 当然也可以放在Service层中统一处理, 不过后期还需要控台即能够看到某个会员的乘客, 也要能查看所有乘客
        PageResp<${Domain}QueryResp> list = ${domain}Service.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {//使用@PathVariable注解,将id与restful格式的路径{id}关联起来
        ${domain}Service.delete(id);
        return new CommonResp<>();
    }


}
