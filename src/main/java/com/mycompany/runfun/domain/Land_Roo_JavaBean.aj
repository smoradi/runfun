// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.runfun.domain;

import com.mycompany.runfun.domain.Land;
import com.mycompany.runfun.domain.User;

privileged aspect Land_Roo_JavaBean {
    
    public String Land.getName() {
        return this.name;
    }
    
    public void Land.setName(String name) {
        this.name = name;
    }
    
    public Double Land.getDistance() {
        return this.distance;
    }
    
    public void Land.setDistance(Double distance) {
        this.distance = distance;
    }
    
    public User Land.getUser() {
        return this.user;
    }
    
    public void Land.setUser(User user) {
        this.user = user;
    }
    
}
