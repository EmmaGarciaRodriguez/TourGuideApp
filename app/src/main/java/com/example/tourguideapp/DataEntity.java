package com.example.tourguideapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_table")
public class DataEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "data_json")
    private String dataJson; // Almacenará la lista String[][] convertida a JSON

    // Constructor, getters y setters

    public DataEntity(int id, String dataJson) {
        this.id = id;
        this.dataJson = dataJson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
}
