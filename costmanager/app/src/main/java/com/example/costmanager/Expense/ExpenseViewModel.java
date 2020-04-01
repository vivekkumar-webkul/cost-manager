package com.example.costmanager.Expense;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.costmanager.Expense.Database.DbInstanceExp;
import com.example.costmanager.Expense.Database.Expense;
import com.example.costmanager.Income.Database.DbInstance;
import com.example.costmanager.Income.Database.Income;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    public DbInstanceExp db;
    private LiveData<List<Expense>> eachExpense;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        db = DbInstanceExp.getInstance(this.getApplication());
    }

    public LiveData<List<Expense>> getUserExpense(int userId){
        return db.getExpenseDao().getExpenseList(userId);
    }

    public void insertExpenseData(Expense myModel)
    {
        db.getExpenseDao().insertExpense(myModel);
    }

    public void deleteExpenseData(Long id)
    {
        db.getExpenseDao().deleteExpense(id);
    }

    public void updateExpenseData(Expense myModel)
    {
        db.getExpenseDao().updateExpense(myModel);
    }
}
