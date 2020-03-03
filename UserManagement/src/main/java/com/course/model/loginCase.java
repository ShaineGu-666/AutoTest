package com.course.model;

import lombok.Data;

@Data
public class loginCase {


    private String username;
    private String password;
    private String expected;
    private int id;

}
