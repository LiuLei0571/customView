package com.example.join.model;

import java.io.Serializable;

/**
 * 用途：
 * 作者：Created by john on 2016/8/3.
 * 邮箱：liulei2@aixuedai.com
 */

public class Contact  implements Serializable {
    private String n;
    private String t;
    private String d;

    public Contact() {
    }

    public Contact(String n, String t) {
        this.n = n;
        this.t = t;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getT() {
        return t;
    }

    public void setT(String telephone) {
        this.t = telephone;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @Override
    public Contact clone(){
        return new Contact(n,t);
    }
}
