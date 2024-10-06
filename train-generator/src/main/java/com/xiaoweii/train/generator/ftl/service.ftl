package com.xiaoweii.train.${module}.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoweii.train.common.resp.PageResp;
import com.xiaoweii.train.common.util.SnowUtil;
import com.xiaoweii.train.${module}.domain.${Domain};
import com.xiaoweii.train.${module}.domain.${Domain}Example;
import com.xiaoweii.train.${module}.mapper.${Domain}Mapper;
import com.xiaoweii.train.${module}.req.${Domain}QueryReq;
import com.xiaoweii.train.${module}.req.${Domain}SaveReq;
import com.xiaoweii.train.${module}.resp.${Domain}QueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ${Domain}Service {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Service.class);

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    //后续界面操作时, 保存后, 界面会刷新列表, 不需要返回保存成功后的数据
    public void save(${Domain}SaveReq req) {
        DateTime now = DateTime.now();
        ${Domain} ${domain} = BeanUtil.copyProperties(req, ${Domain}.class);
        if (ObjectUtil.isNull(${domain}.getId())) {
            ${domain}.setId(SnowUtil.getSnowflakeNextId());
            ${domain}.setCreateTime(now);
            ${domain}.setUpdateTime(now);
            ${domain}Mapper.insert(${domain});
        } else {
            ${domain}.setUpdateTime(now);
            ${domain}Mapper.updateByPrimaryKey(${domain});
        }
    }

    //做成通用的
    public PageResp<${Domain}QueryResp> queryList(${Domain}QueryReq req) {
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        ${domain}Example.setOrderByClause("id desc");//根据id倒序, 目的是为了让最新添加的乘客显示在最前面
        ${Domain}Example.Criteria criteria = ${domain}Example.createCriteria();

        LOG.info("查询页码: {}", req.getPage());
        LOG.info("每页条数: {}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);

        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getTotal());

        List<${Domain}QueryResp> list = BeanUtil.copyToList(${domain}List, ${Domain}QueryResp.class);

        PageResp<${Domain}QueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;

    }

    public void delete(Long id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
	
}
