package com.xiaoweii.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.xiaoweii.train.common.exception.BusinessException;
import com.xiaoweii.train.common.exception.BusinessExceptionEnum;
import com.xiaoweii.train.member.domain.Member;
import com.xiaoweii.train.member.domain.MemberExample;
import com.xiaoweii.train.member.mapper.MemberMapper;
import com.xiaoweii.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    // @Autowired和@Resource都可以, 都可以将接口注入进来
    @Resource
    private MemberMapper memberMapper;

    public long count() {
        return memberMapper.countByExample(null);
    }

    /*
        注册功能
     */
    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);//createCriteria()相当于生成where条件
        List<Member> list = memberMapper.selectByExample(memberExample);

        //Hutool里的工具
        if (CollUtil.isNotEmpty(list)) {
            //return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());//目前用系统时间戳,后期用雪花算法生成id
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
