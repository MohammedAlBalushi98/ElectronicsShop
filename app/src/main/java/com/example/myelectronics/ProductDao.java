package com.example.myelectronics;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    long AddProduct(OrmProduct product);

    @Query("SELECT * FROM products")
    List<OrmProduct> GetAllProducts();

    @Query("SELECT * FROM products WHERE  productId =:id")
    OrmProduct GetProductById(int id);

    @Query("UPDATE products SET productName =:name, productDescription =:desc , productPrice =:price")
    int UpdateUserInfo(String name, String desc, double price);
}
