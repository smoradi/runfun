// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.runfun.domain;

import com.mycompany.runfun.domain.Land;
import com.mycompany.runfun.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Land_Roo_Finder {
    
    public static TypedQuery<Land> Land.findLandsByUser(User user) {
        if (user == null) throw new IllegalArgumentException("The user argument is required");
        EntityManager em = Land.entityManager();
        TypedQuery<Land> q = em.createQuery("SELECT o FROM Land AS o WHERE o.user = :user", Land.class);
        q.setParameter("user", user);
        return q;
    }
    
    public static TypedQuery<Land> Land.findLandsByUserAndIdEquals(User user, Long id) {
        if (user == null) throw new IllegalArgumentException("The user argument is required");
        if (id == null) throw new IllegalArgumentException("The id argument is required");
        EntityManager em = Land.entityManager();
        TypedQuery<Land> q = em.createQuery("SELECT o FROM Land AS o WHERE o.user = :user AND o.id = :id", Land.class);
        q.setParameter("user", user);
        q.setParameter("id", id);
        return q;
    }
    
}
