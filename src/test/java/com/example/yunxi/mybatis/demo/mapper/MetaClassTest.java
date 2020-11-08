package com.example.yunxi.mybatis.demo.mapper;

import lombok.Data;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.junit.Test;

/**
 * @Author: 无双老师【云析学院:http://yunxiedu.net QQ:3190976240 email:zhouguanya20@163.com】
 * @Date: 2020-08-26 16:01
 * @Description: 测试类
 */
public class MetaClassTest {

    @Data
    private class Author {
        private Integer id;
        private String name;
        private Integer age;
        /** 一个作者对应多篇文章 */
        private Article[] articles;
    }

    @Data
    private class Article {
        private Integer id;
        private String title;
        private String content;
        /** 一篇文章对应一个作者 */
        private Author author;
    }

    @Test
    public void testHasSetter() {
        // 为 Author 创建元信息对象
        MetaClass authorMeta = MetaClass.forClass(Author.class, new DefaultReflectorFactory());
        System.out.println("------------☆ Author ☆------------");
        System.out.println("id -> " + authorMeta.hasSetter("id"));
        System.out.println("name -> " + authorMeta.hasSetter("name"));
        System.out.println("age -> " + authorMeta.hasSetter("age"));
        // 检测 Author 中是否包含 Article[] 的 setter
        System.out.println("articles -> " + authorMeta.hasSetter("articles"));
        System.out.println("articles[] -> " + authorMeta.hasSetter("articles[]"));
        System.out.println("title -> " + authorMeta.hasSetter("title"));

        // 为 Article 创建元信息对象
        MetaClass articleMeta = MetaClass.forClass(Article.class, new DefaultReflectorFactory());
        System.out.println("\n------------☆ Article ☆------------");
        System.out.println("id -> " + articleMeta.hasSetter("id"));
        System.out.println("title -> " + articleMeta.hasSetter("title"));
        System.out.println("content -> " + articleMeta.hasSetter("content"));
        // 下面两个均为复杂属性，分别检测 Article 类中的 Author 类是否包含 id 和 name 的 setter 方法
        System.out.println("author.id -> " + articleMeta.hasSetter("author.id"));
        System.out.println("author.name -> " + articleMeta.hasSetter("author.name"));
    }
}