package com.mycompany.runfun.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Land {

    @NotNull
    private String name;

    @NotNull
    private Double distance;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
