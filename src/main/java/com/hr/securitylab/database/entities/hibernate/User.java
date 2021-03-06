package com.hr.securitylab.database.entities.hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by joost on 4-10-2016.
 */

@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    public User() {
    }

    public User(Product product, Set<Role> roles, String username, String password, Date created_at, Date updated_at, Date deleted_at) {
        this.product = product;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.created_at = new Date(created_at.getTime());
        this.updated_at = new Date(updated_at.getTime());
        this.deleted_at = new Date(deleted_at.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return new Date(created_at.getTime());
    }

    public void setCreated_at(Date created_at) {
        this.created_at = new Date(created_at.getTime());
    }

    public Date getUpdated_at() {
        return new Date(updated_at.getTime());
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = new Date(updated_at.getTime());
    }

    public Date getDeleted_at() {
        return new Date(deleted_at.getTime());
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = new Date(deleted_at.getTime());
    }
}
