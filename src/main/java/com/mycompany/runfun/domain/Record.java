package com.mycompany.runfun.domain;

import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Record {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "land_id")
    private Land land;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Land user;

    @Min(1L)
    private Integer laps;

    @NotNull
    private Long time;

    private String comment;
}
