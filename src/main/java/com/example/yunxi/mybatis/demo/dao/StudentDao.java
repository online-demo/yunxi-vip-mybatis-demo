package com.example.yunxi.mybatis.demo.dao;

import com.example.yunxi.mybatis.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-08-02 14:44
 * @Description: 学生相关操作
 */
@Mapper
public interface StudentDao {
    /**
     * 保存学生信息
     *
     * @param student 学生实体
     * @return 影响的行数
     */
    int save(Student student);

    /**
     * 根据ID查询学生信息
     *
     * @param id 主键编号
     * @return 学生信息
     */
    Student query(int id);

    /**
     * 分页查询
     *
     * @param id
     * @param rowBounds
     */
    List<Student> findByPaging(@Param("id") Integer id, RowBounds rowBounds);
}
