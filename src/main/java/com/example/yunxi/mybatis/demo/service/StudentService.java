package com.example.yunxi.mybatis.demo.service;

import com.example.yunxi.mybatis.demo.model.Student;

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-08-02 14:58
 * @Description: 学生服务类
 */
public interface StudentService {
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
}
