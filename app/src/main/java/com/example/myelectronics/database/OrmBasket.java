package com.example.myelectronics.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "baskets")
public class OrmBasket {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int basketId;
    private int productId;
    private int quantity;
    private double total_price;

    public OrmBasket(int productId, int quantity, double total_price, int basketId) {
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
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
        basketId = basketId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
