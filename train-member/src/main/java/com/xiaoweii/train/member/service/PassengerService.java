package com.xiaoweii.train.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.xiaoweii.train.common.util.SnowUtil;
import com.xiaoweii.train.member.domain.Passenger;
import com.xiaoweii.train.member.mapper.PassengerMapper;
import com.xiaoweii.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    //后续界面操作时, 保存后, 界面会刷新列表, 不需要返回保存成功后的数据
    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
