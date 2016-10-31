package com.hr.securitylab.database.entities.hibernate;

import javax.persistence.*;

/**
 * Created by joost on 31-10-2016.
 */

@Entity
@Table(name = "polling")
public class Polling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "polling_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "vibrate")
    private boolean vibrate;

    @Column(name = "hasUpdate")
    private boolean hasUpdate;

    public Polling() {
    }

    public Polling(Product product, boolean vibrate, boolean hasUpdate) {
        this.product = product;
        this.vibrate = vibrate;
        this.hasUpdate = hasUpdate;
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

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public boolean isHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }
}
