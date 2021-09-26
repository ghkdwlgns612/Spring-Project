package com.example.ecommerce.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserForm {
    @NotEmpty(message = "회원이름은 필수 항목입니다.")
    private String name;

    private String city;
    private String street;
    private int zipcode;
}
