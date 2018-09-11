package com.test.dao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.test.db.DaoSession;
import com.test.db.CourseDao;
import com.test.db.UserDao;

@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String number;

    @Generated(hash = 345148039)
    public User(Long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
