package com.example.costmanager.Income.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "income")
public class Income {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;
    private String name;
    private String type;
    private int amount;
    private int uid;

    public Income() {
    }

    public Income(String name, String type, int amount, int uid) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.uid = uid;
    }

    public Income(long id, String name, String type, int amount, int uid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
