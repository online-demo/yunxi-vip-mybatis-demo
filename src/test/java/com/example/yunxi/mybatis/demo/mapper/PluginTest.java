package com.example.yunxi.mybatis.demo.mapper;

import com.example.yunxi.mybatis.demo.YunxiMybatisDemoApplication;
import com.example.yunxi.mybatis.demo.dao.StudentDao;
import com.example.yunxi.mybatis.demo.model.Student;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-11-08 16:01
 * @Description: 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YunxiMybatisDemoApplication.class)
public class PluginTest {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Test
    public void testPlugin() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentDao studentDao = session.getMapper(StudentDao.class);
            List<Student> studentList = studentDao.findByPaging(1, new RowBounds(1, 2));
            System.out.println("studentList" + studentList);
        } finally {
            session.close();
        }
    }
}
