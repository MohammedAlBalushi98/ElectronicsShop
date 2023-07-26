package com.example.myelectronics;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    long AddUser(OrmUser user);

    @Query("SELECT * FROM users")
    List<OrmUser> GetAllUsers();

    @Query("SELECT * FROM users WHERE email =:email")
    OrmUser GetUserById(String email);

    @Query("UPDATE users SET email = :email, password = :password ,firstName = :firstName, lastName = :lastName, balance = :balance")
    int UpdateUserInfo(String email, String password, String firstName, String lastName, double balance);

}
