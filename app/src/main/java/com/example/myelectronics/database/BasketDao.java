package com.example.myelectronics.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BasketDao {
    @Insert
    long AddBasket(OrmBasket basket);

    @Query("SELECT * FROM baskets")
    List<OrmBasket> GetBasket();

    @Query("SELECT * FROM baskets WHERE  id =:id")
    OrmBasket GetBasketById(int id);

    @Query("UPDATE baskets SET productId =:productID, quantity =:quantity, total_price =:totalPrice WHERE id =:id")
    int UpdateBasketInfo(int id, int productID, int quantity, double totalPrice);
}
