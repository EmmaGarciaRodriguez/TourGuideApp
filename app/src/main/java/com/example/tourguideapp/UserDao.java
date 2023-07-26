package com.example.tourguideapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where userId=(:userId) and password=(:password)")
    UserEntity login(String userId, String password);

    @Query("SELECT * FROM users WHERE userId = :userId")
    UserEntity getCurrentUser(String userId);

    @Update
    void updateUser(UserEntity user);

}
