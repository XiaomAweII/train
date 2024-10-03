package com.xiaoweii.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.xiaoweii.train.member.domain.Member;
import com.xiaoweii.train.member.domain.MemberExample;
import com.xiaoweii.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    // @Autowired和@Resource都可以, 都可以将接口注入进来
    @Resource
    private MemberMapper memberMapper;

    public Long count() {
        return memberMapper.countByExample(null);
    }

    /*
        注册功能
     */
    public long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);//createCriteria()相当于生成where条件
        List<Member> list = memberMapper.selectByExample(memberExample);

        //Hutool里的工具
        if (CollUtil.isNotEmpty(list)) {
            /*
            方式一:
            带验证码的注册可以用这种方式, 有验证码, 说明手机号是本人用, 原来注册过的, 就不用再保存了, 直接把数据库返回.
            这个接口做成既可以是注册, 也可以是登录
             */
            //return list.get(0).getId();

            throw new RuntimeException("手机号已注册");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());//目前用系统时间戳,后期用雪花算法生成id
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
