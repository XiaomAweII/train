package com.xiaoweii.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.xiaoweii.train.common.exception.BusinessException;
import com.xiaoweii.train.common.exception.BusinessExceptionEnum;
import com.xiaoweii.train.common.util.SnowUtil;
import com.xiaoweii.train.member.domain.Member;
import com.xiaoweii.train.member.domain.MemberExample;
import com.xiaoweii.train.member.mapper.MemberMapper;
import com.xiaoweii.train.member.req.MemberRegisterReq;
import com.xiaoweii.train.member.req.MemberSendCodeReq;
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
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);//createCriteria()相当于生成where条件
        List<Member> list = memberMapper.selectByExample(memberExample);

        //Hutool里的工具
        if (CollUtil.isNotEmpty(list)) {
            //return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }


        /*
         * 这个系统的时间戳, 如果在高并发的场景当中会出现重复, 即使加随机数, 在数据访问量极大的情况下仍然会重复
         * 1. 自增ID不适合分布式数据库, 分库分表场景. 只适合小型项目.
         * 2. UUID会影响索引效率, 因为UUID是无序的, 用一对无序的ID来构建一个有序的索引目录, 性能上肯定有问题, 它有时候会插入在前边, 有时插入到后边, 有性能损耗
         * 3. 雪花算法: 雪花算法的特点就是我们生成的ID是不断增长的, 由Twitter公司在2014年开源scala语言版本
         * Hutool也集成了雪花算法, 有个类叫做IdUtil, 官方文档连接:https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%94%AF%E4%B8%80ID%E5%B7%A5%E5%85%B7-IdUtil
         *
         * */

        /*
         * snowflake-64bit
         * 首位符号位+41bit_时间戳+10bit_工作机器id+12bit_序列号
         * 工作机器id也分为两部分, 第一部分是数据中心(5bit), 第二部分是机器编号(5bit)
         * 统一毫秒同一台机器, 使用序列号,4000多个 如果生成满了之后就会阻塞, 当然我们会设置多个节点, 避免崩溃
         *
         * 数据中心, 机器ID怎么设置?
         * - 利用redis自增序列
         * - 利用数据库, 为每台机器分配workId, 保存ip和workId的关系
         *
         * 时钟回拨问题?
         * 比如每一台机器可能跟北京时间有差异, 每过一段时间进行回拨
         * 假如机器时间是3点, 北京时间是2点, 此时需要把机器时间调回2点, 那么2点~3点的ID会重新再生成一遍
         * 很简单, 暂时不启动服务器即可, 等时间到了, 进行启动
         *
         * */

        /**
         * 为每个数据中心和机器编号, 保证每台机器生产的ID不重复
         * */
        Member member = new Member();
//        member.setId(IdUtil.getSnowflake(1,1).nextId());//封装前
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
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        //如果手机号不存在, 则插入一条记录
        if (CollUtil.isEmpty(list)) {
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
}
