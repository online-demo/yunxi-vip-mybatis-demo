package com.example.yunxi.mybatis.demo.mapper;

import com.example.yunxi.mybatis.demo.YunxiMybatisDemoApplication;
import com.example.yunxi.mybatis.demo.dao.TeacherMapper;
import com.example.yunxi.mybatis.demo.model.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-08-18 18:57
 * @Description: 老师测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YunxiMybatisDemoApplication.class)
@Slf4j
public class TeacherMapperTest2 {
    @Autowired
    private SqlSessionFactory factory;

    /**
     * 实验1:
     * 测试二级缓存效果，不提交事务，sqlSession1查询完数据后，
     * sqlSession2相同的查询是否会从缓存中获取数据。
     */
    @Test
    public void testCacheWithoutCommitOrClose() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务
        SqlSession sqlSession2 = factory.openSession(true); // 自动提交事务

        TeacherMapper teacherMapper = sqlSession1.getMapper(TeacherMapper.class);
        TeacherMapper teacherMapper2 = sqlSession2.getMapper(TeacherMapper.class);
        Teacher teacher = buildTeacher("wushuang");
        teacherMapper.insertSelective(teacher);
        System.out.println("teacherMapper读取数据: " + teacherMapper.selectByPrimaryKey(teacher.getId()));
        System.out.println("teacherMapper2读取数据: " + teacherMapper2.selectByPrimaryKey(teacher.getId()));

    }

    private Teacher buildTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setNickname(name);
        teacher.setAge(35);
        teacher.setGender("MALE");
        teacher.setCreatetime(new Date());
        teacher.setUpdatetime(new Date());
        return teacher;
    }

    /**
     * 实验2:
     * 测试二级缓存效果，当提交事务时，sqlSession1查询完数据后，
     * sqlSession2相同的查询是否会从缓存中获取数据。
     */
    @Test
    public void testCacheWithCommitOrClose() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务
        SqlSession sqlSession2 = factory.openSession(true); // 自动提交事务

        TeacherMapper teacherMapper = sqlSession1.getMapper(TeacherMapper.class);
        TeacherMapper teacherMapper2 = sqlSession2.getMapper(TeacherMapper.class);
        Teacher teacher = buildTeacher("qingtian");
        teacherMapper.insertSelective(teacher);
        System.out.println("teacherMapper读取数据: " + teacherMapper.selectByPrimaryKey(teacher.getId()));
        sqlSession1.close();
        System.out.println("teacherMapper2读取数据: " + teacherMapper2.selectByPrimaryKey(teacher.getId()));

    }

    /**
     * 实验3:
     * 测试update操作是否会刷新该namespace下的二级缓存
     */
    @Test
    public void testCacheWithUpdate() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务
        SqlSession sqlSession2 = factory.openSession(true); // 自动提交事务
        SqlSession sqlSession3 = factory.openSession(true); // 自动提交事务

        TeacherMapper teacherMapper = sqlSession1.getMapper(TeacherMapper.class);
        TeacherMapper teacherMapper2 = sqlSession2.getMapper(TeacherMapper.class);
        TeacherMapper teacherMapper3 = sqlSession3.getMapper(TeacherMapper.class);

        Teacher teacher = buildTeacher("xiaowu");
        teacherMapper.insertSelective(teacher);

        System.out.println("teacherMapper读取数据: " + teacherMapper.selectByPrimaryKey(teacher.getId()));
        sqlSession1.close();
        System.out.println("teacherMapper2读取数据: " + teacherMapper2.selectByPrimaryKey(teacher.getId()));
        teacherMapper3.updateNameByPrimaryKey(teacher.getId(), "zhangsan");
        sqlSession3.commit();
        System.out.println("teacherMapper2读取数据: " + teacherMapper2.selectByPrimaryKey(teacher.getId()));

    }
}
