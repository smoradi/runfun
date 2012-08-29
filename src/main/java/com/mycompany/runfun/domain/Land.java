package com.mycompany.runfun.domain;

import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findLandsByUser", "findLandsByUserAndIdEquals" })
public class Land {

    @NotNull
    private String name;

    @NotNull
    private Double distance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String toString() {
    	return getName() + " " + getDistance();
    }
    
    public static List<Land> findLandEntries(User user, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Land o WHERE o.user = :user", Land.class).setParameter("user", user).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static long countLands(User user) {
        return entityManager().createQuery("SELECT COUNT(o) FROM Land o WHERE o.user = :user", Long.class).setParameter("user", user).getSingleResult();
    }
}
