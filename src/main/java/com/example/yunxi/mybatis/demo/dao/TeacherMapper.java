package com.example.yunxi.mybatis.demo.dao;

import com.example.yunxi.mybatis.demo.model.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    int updateNameByPrimaryKey(@Param("id") int id, @Param("name") String name);
}