package com.example.yunxi.mybatis.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Teacher implements Serializable {
    private Integer id;

    private String name;

    private String nickname;

    private Integer age;

    private String gender;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Teacher(Integer id, String name, String nickname, Integer age, String gender, Date createtime, Date updatetime) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Teacher() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", nickname=").append(nickname);
        sb.append(", age=").append(age);
        sb.append(", gender=").append(gender);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}