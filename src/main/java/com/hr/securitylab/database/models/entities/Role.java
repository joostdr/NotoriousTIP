package com.hr.securitylab.database.models.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Joost on 15-10-2016.
 */

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;

    public Role() {
    }

    public Role(int id, String role, Set<User> users) {
        this.id = id;
        this.role = role;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
