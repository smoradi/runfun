package com.mycompany.runfun.domain;

import java.util.Date;
import java.util.List;

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
@RooJpaActiveRecord(finders = { "findRecordsByUserAndIdEquals", "findRecordsByUser" })
public class Record {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "land_id")
    private Land land;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Min(1L)
    private Integer laps;

    @NotNull
    private Long time;

    private String comment;
    
    public static List<Record> findRecordEntries(User user, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Record o WHERE o.user = :user", Record.class).setParameter("user", user).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static long countRecords(User user) {
        return entityManager().createQuery("SELECT COUNT(o) FROM Record o WHERE o.user = :user", Long.class).setParameter("user", user).getSingleResult();
    }

}
