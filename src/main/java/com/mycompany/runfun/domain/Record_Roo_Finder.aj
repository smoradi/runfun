// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.runfun.domain;

import com.mycompany.runfun.domain.Record;
import com.mycompany.runfun.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Record_Roo_Finder {
    
    public static TypedQuery<Record> Record.findRecordsById(Long id) {
        if (id == null) throw new IllegalArgumentException("The id argument is required");
        EntityManager em = Record.entityManager();
        TypedQuery<Record> q = em.createQuery("SELECT o FROM Record AS o WHERE o.id = :id", Record.class);
        q.setParameter("id", id);
        return q;
    }
    
    public static TypedQuery<Record> Record.findRecordsByUser(User user) {
        if (user == null) throw new IllegalArgumentException("The user argument is required");
        EntityManager em = Record.entityManager();
        TypedQuery<Record> q = em.createQuery("SELECT o FROM Record AS o WHERE o.user = :user", Record.class);
        q.setParameter("user", user);
        return q;
    }
    
    public static TypedQuery<Record> Record.findRecordsByUserAndIdEquals(User user, Long id) {
        if (user == null) throw new IllegalArgumentException("The user argument is required");
        if (id == null) throw new IllegalArgumentException("The id argument is required");
        EntityManager em = Record.entityManager();
        TypedQuery<Record> q = em.createQuery("SELECT o FROM Record AS o WHERE o.user = :user AND o.id = :id", Record.class);
        q.setParameter("user", user);
        q.setParameter("id", id);
        return q;
    }
    
}
