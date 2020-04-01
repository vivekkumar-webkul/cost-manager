package com.example.costmanager.Income.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Income.class}, version = 1)
public abstract class DbInstance extends RoomDatabase {
    private static DbInstance instance;
    public static DbInstance getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DbInstance.class,
                    "income"
            ).build();
        }
        return instance;
    }
    public abstract Dao getDao();
}
