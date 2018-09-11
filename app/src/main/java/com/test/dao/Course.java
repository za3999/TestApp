package com.test.dao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Course {

    @Id
    private long id;

    private String name;

    private String number;

    @Generated(hash = 1426319705)
    public Course(long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    @Generated(hash = 1355838961)
    public Course() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
