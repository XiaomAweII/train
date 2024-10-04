package com.xiaoweii.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.xiaoweii.train.common.exception.BusinessException;
import com.xiaoweii.train.common.exception.BusinessExceptionEnum;
import com.xiaoweii.train.common.util.SnowUtil;
import com.xiaoweii.train.member.domain.Member;
import com.xiaoweii.train.member.domain.MemberExample;
import com.xiaoweii.train.member.mapper.MemberMapper;
import com.xiaoweii.train.member.req.MemberLoginReq;
import com.xiaoweii.train.member.req.MemberRegisterReq;
import com.xiaoweii.train.member.req.MemberSendCodeReq;
import com.xiaoweii.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

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
        Member memberDB = selectByMobile(mobile);

        //Hutool里的工具
        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());//封装后
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    /*
     * 生成验证码
     * */
    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        //如果手机号不存在, 则插入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            LOG.info("手机号不存在, 插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号已存在, 不插入");
        }


        // 生成验证码
//        String code = RandomUtil.randomString(4);
        String code = "8888";//方便测试, 固定短信验证码
        LOG.info("生成短信验证码:{}", code);

        // 保存短信记录表: 手机号, 短信验证码, 有效期, 是否已使用, 业务类型, 发送时间, 使用时间
        LOG.info("保存短信记录表");

        // 对接短信通道, 发送短信
        LOG.info("对接短信通道");

    }

    /*
     * 登录
     * */
    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);
        /*
         * 正常情况下应该把memberDB会员信息返回,可能包括昵称头像,
         * 但是像密码一般会加密存储, 可是仍然不应该返回给前端
         * */

        //如果手机号不存在, 则插入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            //没有手机号抛出异常
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        // 校验短信验证码
        if (!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        //Hutool提供的工具类
        //参数一:赋值源, 参数二:复制成什么类

        return BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }

    }
}
