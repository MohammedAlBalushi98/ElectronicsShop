package com.example.myelectronics.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BasketDao {
    @Insert
    long AddBasket(OrmBasket basket);

    @Query("SELECT * FROM baskets")
    List<OrmBasket> GetBasket();

    @Query("SELECT * FROM baskets WHERE basketId =:id")
    List<OrmBasket> GetBasketById(int id);

    @Query("UPDATE baskets SET productId =:productID, quantity =:quantity, total_price =:totalPrice, basketId =:basketId WHERE id =:id")
    int UpdateBasketInfo(int id, int productID, int quantity, double totalPrice, int basketId);

    @Delete
    int RemoveBasket(OrmBasket basket);
}
