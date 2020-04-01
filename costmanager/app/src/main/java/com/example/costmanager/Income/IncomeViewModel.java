package com.example.costmanager.Income;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;

import com.example.costmanager.Income.Database.DbInstance;
import com.example.costmanager.Income.Database.Income;

import java.util.List;

public class IncomeViewModel extends AndroidViewModel {
    public DbInstance db;
    private LiveData<List<Income>> eachIncome;

    public IncomeViewModel(@NonNull Application application) {
        super(application);
        db = DbInstance.getInstance(this.getApplication());
    }

    public LiveData<List<Income>> getUserIncome(int userId){
        return db.getDao().getIncomeList(userId);
    }

    public void insertData(Income myModel)
    {
        db.getDao().insert(myModel);
    }

    public void deleteData(Long id)
    {
        db.getDao().delete(id);
    }

    public void updateData(Income myModel)
    {
        db.getDao().update(myModel);
    }
}
