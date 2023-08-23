package com.cydeo.dto;

import com.cydeo.enus.Gender;
import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
    private String passWord;
    private Gender gender;
    private RoleDTO role;


}
