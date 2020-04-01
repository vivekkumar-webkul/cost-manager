package com.example.costmanager.Expense.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "expense")
public class Expense {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;
    private int price;
    private String name;
    private String type;
    private int date;
    private int parts;
    private int partRemaining;
    private int partDone;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getPartRemaining() {
        return partRemaining;
    }

    public void setPartRemaining(int partRemaining) {
        this.partRemaining = partRemaining;
    }

    public int getPartDone() {
        return partDone;
    }

    public void setPartDone(int partDone) {
        this.partDone = partDone;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }
}
