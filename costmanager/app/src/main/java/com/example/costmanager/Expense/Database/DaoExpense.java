package com.example.costmanager.Expense.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.costmanager.Income.Database.Income;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoExpense {
    @Query("SELECT * FROM expense WHERE id=:user_id")
    LiveData<List<Expense>> getExpenseList(int user_id);

    @Insert(onConflict = REPLACE)
    public void insertExpense(Expense expense);

    @Query("DELETE FROM expense WHERE id=:id")
    public void deleteExpense(Long id);

    @Update
    public void updateExpense(Expense expense);
}
