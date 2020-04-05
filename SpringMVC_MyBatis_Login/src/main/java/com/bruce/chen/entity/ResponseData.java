package com.bruce.chen.entity;

public class ResponseData {

    private String rescode;
    private String resdesc;
    private String ext;

    public ResponseData() {
    }

    public ResponseData(String rescode, String resdesc, String ext) {
        this.rescode = rescode;
        this.resdesc = resdesc;
        this.ext = ext;
    }

    public String getRescode() {
        return rescode;
    }

    public ResponseData setRescode(String rescode) {
        this.rescode = rescode;
        return this;
    }

    public String getResdesc() {
        return resdesc;
    }

    public ResponseData setResdesc(String resdesc) {
        this.resdesc = resdesc;
        return this;
    }

    public String getExt() {
        return ext;
    }

    public ResponseData setExt(String ext) {
        this.ext = ext;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "rescode='" + rescode + '\'' +
                ", resdesc='" + resdesc + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}
