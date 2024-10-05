package com.xiaoweii.train.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoweii.train.common.context.LoginMemberContext;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.common.util.SnowUtil;
import com.xiaoweii.train.member.domain.Passenger;
import com.xiaoweii.train.member.domain.PassengerExample;
import com.xiaoweii.train.member.mapper.PassengerMapper;
import com.xiaoweii.train.member.req.PassengerQueryReq;
import com.xiaoweii.train.member.req.PassengerSaveReq;
import com.xiaoweii.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PassengerService {

    private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper PassengerMapper;

    //后续界面操作时, 保存后, 界面会刷新列表, 不需要返回保存成功后的数据
    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger Passenger = BeanUtil.copyProperties(req, Passenger.class);
        if (ObjectUtil.isNull(Passenger.getId())) {
            Passenger.setMemberId(LoginMemberContext.getId());
            Passenger.setId(SnowUtil.getSnowflakeNextId());
            Passenger.setCreateTime(now);
            Passenger.setUpdateTime(now);
            PassengerMapper.insert(Passenger);
        } else {
            Passenger.setUpdateTime(now);
            PassengerMapper.updateByPrimaryKey(Passenger);
        }
    }

    //做成通用的
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample PassengerExample = new PassengerExample();
        PassengerExample.setOrderByClause("id desc");//根据id倒序, 目的是为了让最新添加的乘客显示在最前面
        PassengerExample.Criteria criteria = PassengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }

        LOG.info("查询页码: {}", req.getPage());
        LOG.info("每页条数: {}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Passenger> PassengerList = PassengerMapper.selectByExample(PassengerExample);

        PageInfo<Passenger> pageInfo = new PageInfo<>(PassengerList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getTotal());

        List<PassengerQueryResp> list = BeanUtil.copyToList(PassengerList, PassengerQueryResp.class);

        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;

    }

    public void delete(Long id) {
        PassengerMapper.deleteByPrimaryKey(id);
    }
	
}
