package com.example.costmanager.Income.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@androidx.room.Dao
public interface Dao {
    @Query("SELECT * FROM income WHERE uid=:user_id")
    LiveData<List<Income>> getIncomeList(int user_id);

    @Insert(onConflict = REPLACE)
    public void insert(Income income);

    @Query("DELETE FROM income WHERE id=:id")
    public void delete(Long id);

    @Update
    public void update(Income income);
}
