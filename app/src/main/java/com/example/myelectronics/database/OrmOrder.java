package com.example.myelectronics.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class OrmOrder {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int basketId;
    private double total_price;

    public OrmOrder(int id, int basketId, double total_price) {
        this.id = id;
        this.basketId = basketId;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
