package com.biblioP7.beans;

import lombok.Data;

@Data
public class RegisterForm {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String repassword;

}
