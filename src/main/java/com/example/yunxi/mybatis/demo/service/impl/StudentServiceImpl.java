package com.example.yunxi.mybatis.demo.service.impl;

import com.example.yunxi.mybatis.demo.dao.StudentDao;
import com.example.yunxi.mybatis.demo.model.Student;
import com.example.yunxi.mybatis.demo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-08-02 14:59
 * @Description: 学生服务实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    /**
     * 保存学生信息
     *
     * @param student 学生实体
     * @return 影响的行数
     */
    @Override
    public int save(Student student) {
        return studentDao.save(student);
    }

    /**
     * 根据ID查询学生信息
     *
     * @param id 主键编号
     * @return 学生信息
     */
    @Override
    public Student query(int id) {
        return studentDao.query(id);
    }
}
