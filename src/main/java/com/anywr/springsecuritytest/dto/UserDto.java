package com.anywr.springsecuritytest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"credentialsNonExpired", "accountNonLocked", "accountNonExpired", "enabled", "authorities"})
public class UserDto implements UserDetails {

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
    private String password;

    @Pattern(regexp = "^(.+)@(.+)$", message = "The email provided is invalid")
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
