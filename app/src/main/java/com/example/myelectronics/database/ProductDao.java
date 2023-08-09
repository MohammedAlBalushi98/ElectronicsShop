package com.example.myelectronics.database;

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

    @Query("SELECT * FROM products WHERE productId =:id")
    OrmProduct GetProductById(int id);

    @Query("UPDATE products SET productName =:name, productDescription =:desc , category =:category,image =:image, productPrice =:price WHERE productId =:id")
    int UpdateProductInfo(int id, String name, String desc, double price, String category, int image);
}
