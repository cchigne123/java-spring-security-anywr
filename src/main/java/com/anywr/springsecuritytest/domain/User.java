package com.anywr.springsecuritytest.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "A username must be provided")
    @Length(max = 15, message = "Username can have up to 15 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username has to be alphanumeric. No special characters allowed")
    private String username;

    @NotBlank(message = "A name must be provided")
    @Length(max = 100, message = "Name can have up to 100 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name has to be consisted of letters only")
    private String name;

    @Length(max = 100, message = "Last name can have up to 100 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name has to be consisted of letters only")
    private String lastname;

    @NotBlank(message = "A password must be provided")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password has to be alphanumeric. No special characters allowed")
    private String password;

    @Pattern(regexp = "^(.+)@(.+)$", message = "The email provided is invalid")
    private String email;

}
