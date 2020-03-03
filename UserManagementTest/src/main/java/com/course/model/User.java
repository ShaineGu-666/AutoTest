package com.course.model;

import lombok.Data;

@Data
public class User {
    private int uid;
    private String username;
    private String password;
    private String name;
    private String sex;
    private int age;
    private int permission;
    private int isDelete;

    public String toString() {

        return ("uid:" + uid + "," +
                "username:" + username + "," +
                "password:" + password + "," +
                "name:" + name + "," +
                "sex:" + sex + "," +
                "age:" + age + "," +
                "permission:" + permission + "," +
                "isDelete:" + isDelete);
    }

}
