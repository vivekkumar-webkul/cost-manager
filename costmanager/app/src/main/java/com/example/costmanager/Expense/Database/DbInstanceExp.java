package com.example.costmanager.Expense.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.costmanager.Income.Database.Income;

@Database(entities = {Expense.class}, version = 1)
public abstract class DbInstanceExp extends RoomDatabase {
    private static DbInstanceExp mInstance;
    public static DbInstanceExp getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DbInstanceExp.class,
                    "expense"
            ).build();
        }
        return mInstance;
    }
    public abstract DaoExpense getExpenseDao();
}
