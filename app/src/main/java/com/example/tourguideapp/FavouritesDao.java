package com.example.tourguideapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourites(FavouritesEntity favouritesEntity);

    @Query("SELECT * FROM favourites WHERE user_id =(:userId)")
    FavouritesEntity getFavouritesByUserId(String userId);

    // Otros métodos que puedas necesitar
}