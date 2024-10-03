package com.xiaoweii.train.member.mapper;

import com.xiaoweii.train.member.domain.Member;
import com.xiaoweii.train.member.domain.MemberExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    long countByExample(MemberExample example);//根据某一个条件查数量

    int deleteByExample(MemberExample example);//根据某条件来删除

    int deleteByPrimaryKey(Long id);//根据主键删除

    int insert(Member record);//插入

    int insertSelective(Member record);//member有值的属性才会生成sql语句

    List<Member> selectByExample(MemberExample example);//根据某条件查找

    Member selectByPrimaryKey(Long id);//主键查找

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);//根据某个条件更新,有值才更新

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);//有空值也会更新

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}