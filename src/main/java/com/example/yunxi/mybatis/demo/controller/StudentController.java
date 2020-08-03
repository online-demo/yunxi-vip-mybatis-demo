package com.example.yunxi.mybatis.demo.controller;

import com.example.yunxi.mybatis.demo.model.Student;
import com.example.yunxi.mybatis.demo.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-08-02 15:01
 * @Description: 学生控制器
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/save")
    public int save(Student student) {
        return studentService.save(student);
    }

    @RequestMapping("/query")
    public Student query(int id) {
        return studentService.query(id);
    }
}
