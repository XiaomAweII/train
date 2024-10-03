package com.xiaoweii.train.member.service;

import com.xiaoweii.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    // @Autowired和@Resource都可以, 都可以将接口注入进来
    @Autowired
    private MemberMapper memberMapper;

    public int count() {
        return memberMapper.count();
    }
}
