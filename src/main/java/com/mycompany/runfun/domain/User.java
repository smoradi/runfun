package com.mycompany.runfun.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "usr", finders = { "findUsersByUsernameEquals" })
public class User {

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    
    @Override
    public String toString() {
    	return getUsername();
    }
}
