package com.bruce.chen.entity;

public class SpringmvcUser {

    private int id;
    private String uname;
    private String upwd;


    public SpringmvcUser(String uname, String upwd) {
        this.uname = uname;
        this.upwd = upwd;
    }

    public SpringmvcUser(int id, String uname, String upwd) {
        this.id = id;
        this.uname = uname;
        this.upwd = upwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    @Override
    public String toString() {
        return "SpringmvcUser{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                '}';
    }
}
