package com.example.myelectronics.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")

public class OrmProduct {
    @PrimaryKey(autoGenerate = true)
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String category;
    private int image;

    public OrmProduct(String productName, String productDescription, double productPrice, String category, int image) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.category = category;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


}
