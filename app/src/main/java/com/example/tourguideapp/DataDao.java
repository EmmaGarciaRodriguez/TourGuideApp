package com.example.tourguideapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(DataEntity data);

    @Query("SELECT * FROM data_table WHERE id = :id")
    DataEntity getDataById(int id);

    // Otros métodos para actualizar o eliminar datos según tus necesidades
}
