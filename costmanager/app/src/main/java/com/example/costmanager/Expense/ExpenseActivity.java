package com.example.costmanager.Expense;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.costmanager.Expense.Database.Expense;
import com.example.costmanager.Income.AddIncomeDialog;
import com.example.costmanager.Income.Database.Income;
import com.example.costmanager.Income.IncomeActivity;
import com.example.costmanager.Income.IncomeListAdapter;
import com.example.costmanager.Income.IncomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.costmanager.R;

import java.util.List;

public class ExpenseActivity extends AppCompatActivity implements AddExpenseDialog.ExpenseDialogListner, ExpenseListAdapter.ExpenseAdapterCallback {
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        SharedPreferences sh = getSharedPreferences("CostManagerSharedPrefereces", MODE_PRIVATE);
        userId = sh.getInt("userId",0);

        RecyclerView listOfIncomes = findViewById(R.id.expense_list);
        listOfIncomes.setLayoutManager(new LinearLayoutManager(this));
        listOfIncomes.setHasFixedSize(true);
        final ExpenseListAdapter adapter = new ExpenseListAdapter(ExpenseActivity.this);
        listOfIncomes.setAdapter(adapter);
//
//        incomeVm = ViewModelProviders.of(this).get(IncomeViewModel.class);
//        incomeVm.getUserIncome(userId).observe(this, new Observer<List<Expense>>() {
//            @Override
//            public void onChanged(List<Expense> expense) {
//                adapter.setIncomeList(expense);
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                AddExpenseDialog dialog = new AddExpenseDialog();
                dialog.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public void update(Expense expense) {

    }

    @Override
    public void selected(String name, String type, int amount, Long id) {

    }
}
