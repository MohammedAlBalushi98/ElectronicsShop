package com.example.myelectronics.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    long AddOrder(OrmOrder order);

    @Query("SELECT * FROM orders")
    List<OrmOrder> GetOrders();

    @Query("SELECT * FROM orders WHERE  id =:id")
    OrmOrder GetOrderById(int id);

    @Query("UPDATE orders SET total_price =:totalPrice, basketId =:basketId WHERE id =:id")
    int UpdateOrderInfo(int id, double totalPrice, int basketId);
}
