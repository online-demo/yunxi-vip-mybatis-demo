package com.example.yunxi.mybatis.demo.mapper;

import java.util.Date;

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

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-08-18 18:57
 * @Description: 老师测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YunxiMybatisDemoApplication.class)
@Slf4j
public class TeacherMapperTest {
    @Autowired
    private SqlSessionFactory factory;

    @Test
    public void showDefaultCacheConfiguration() {
        System.out.println("本地缓存范围: " + factory.getConfiguration().getLocalCacheScope());
        System.out.println("一级缓存是否被启用: " + factory.getConfiguration().isCacheEnabled());
    }

    /**
     * 实验1:
     * 开启一级缓存，范围为会话级别
     * 只有第一次真正查询了数据库，后续的查询使用了一级缓存
     */
    @Test
    public void testLocalCache() {
        SqlSession sqlSession = factory.openSession(true); // 自动提交事务
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = buildTeacher("wushuang");
        teacherMapper.insertSelective(teacher);
        System.out.println(teacherMapper.selectByPrimaryKey(teacher.getId()));
        System.out.println(teacherMapper.selectByPrimaryKey(teacher.getId()));
        System.out.println(teacherMapper.selectByPrimaryKey(teacher.getId()));
        sqlSession.close();
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
     * 实验2：
     * 增加了对数据库的修改操作，验证在一次数据库会话中，如果对数据库发生了修改操作，一级缓存是否会失效。
     * 在修改操作后执行的相同查询，查询了数据库，一级缓存失效
     */
    @Test
    public void testLocalCacheClear() {
        SqlSession sqlSession = factory.openSession(true); // 自动提交事务
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = buildTeacher("qingtian");
        teacherMapper.insertSelective(teacher);
        System.out.println(teacherMapper.selectByPrimaryKey(teacher.getId()));
        System.out.println(teacherMapper.selectByPrimaryKey(teacher.getId()));
        System.out.println(teacherMapper.selectByPrimaryKey(teacher.getId()));
        System.out.println("增加了" + teacherMapper.insertSelective(buildTeacher("xiaowu")) + "个老师");
        System.out.println(teacherMapper.selectByPrimaryKey(teacher.getId()));
        sqlSession.close();
    }

    /**
     * 实验3：
     * 开启两个SqlSession，在sqlSession1中查询数据，使一级缓存生效，在sqlSession2中更新数据库，验证一级缓存只在数据库会话内部共享。
     * sqlSession2更新了id为1的记录，但session1之后的查询中，出现了脏数据，也证明了之前的设想，一级缓存只在数据库会话内部共享。
     */
    @Test
    public void testLocalCacheScope() {
        SqlSession sqlSession1 = factory.openSession(true); // 自动提交事务
        SqlSession sqlSession2 = factory.openSession(true); // 自动提交事务
        TeacherMapper teacherMapper1 = sqlSession1.getMapper(TeacherMapper.class);
        TeacherMapper teacherMapper2 = sqlSession2.getMapper(TeacherMapper.class);
        System.out.println("teacherMapper1读取数据: " + teacherMapper1.selectByPrimaryKey(1));
        System.out.println("teacherMapper1读取数据: " + teacherMapper1.selectByPrimaryKey(1));
        System.out.println("teacherMapper2更新了" + teacherMapper2.updateNameByPrimaryKey(1, "zhangsan") + "个老师的数据");
        System.out.println("teacherMapper1读取数据: " + teacherMapper1.selectByPrimaryKey(1));
        System.out.println("teacherMapper2读取数据: " + teacherMapper2.selectByPrimaryKey(1));
        sqlSession1.close();
        sqlSession2.close();
    }
}
