package com.course.model;

import lombok.Data;

@Data
public class addUserCase {

    private String username;
    private String password;
    private String name;
    private String sex;
    private int age;
    private int permission;
    private int isDelete;
    private int expected;
    private int id;
}
