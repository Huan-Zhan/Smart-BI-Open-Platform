package com.user.center.example.usercenter.model;


import lombok.Data;

//@TableName("user")
@Data
public class student {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
