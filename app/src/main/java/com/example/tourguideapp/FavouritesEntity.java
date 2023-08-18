package com.example.tourguideapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourites")
public class FavouritesEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "favourite_positions_json")
    private String favouritePositionsJson;

    public FavouritesEntity(int id, String userId, String favouritePositionsJson) {
        this.id = id;
        this.userId = userId;
        this.favouritePositionsJson = favouritePositionsJson;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFavouritePositionsJson() {
        return favouritePositionsJson;
    }

    public void setFavouritePositionsJson(String favouritePositionsJson) {
        this.favouritePositionsJson = favouritePositionsJson;
    }
}