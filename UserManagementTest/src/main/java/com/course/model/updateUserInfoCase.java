package com.course.model;

import lombok.Data;

@Data
public class updateUserInfoCase {


    private String username;
    private String sex;
    private int age;
    private int permission;
    private int isDelete;
    private String expected;
    private int id;
    private int uid;
}
