package com.example.costmanager.Income;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.costmanager.Income.Database.Income;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.costmanager.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class IncomeActivity extends AppCompatActivity implements AddIncomeDialog.IncomeDialogListner, IncomeListAdapter.InocomeAdapterCallback{

    private IncomeViewModel incomeVm;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sh = getSharedPreferences("CostManagerSharedPrefereces", MODE_PRIVATE);
        userId = sh.getInt("userId",0);

        setContentView(R.layout.activity_income);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView listOfIncomes = findViewById(R.id.income_list);
        listOfIncomes.setLayoutManager(new LinearLayoutManager(this));
        listOfIncomes.setHasFixedSize(true);
        final IncomeListAdapter adapter = new IncomeListAdapter(IncomeActivity.this);
        listOfIncomes.setAdapter(adapter);

        incomeVm = ViewModelProviders.of(this).get(IncomeViewModel.class);
        incomeVm.getUserIncome(userId).observe(this, new Observer<List<Income>>() {
            @Override
            public void onChanged(List<Income> incomes) {
                adapter.setIncomeList(incomes);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIncomeDialog dialog = new AddIncomeDialog();
                dialog.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public void selected(String name, String type, int amount, Long id) {
        Income income = new Income();
        if(id!=null)
        {
           income.setId(id);
        }
        income.setName(name);
        income.setAmount(amount);
        income.setType(type);
        income.setUid(userId);
        new UpdateVM(IncomeActivity.this).execute(income);
    }

    @Override
    public void update(Income income) {
        AddIncomeDialog dialog = new AddIncomeDialog(income);
        dialog.show(getSupportFragmentManager(), null);
    }

    class UpdateVM extends AsyncTask<Income, Void, Boolean>
    {
        private WeakReference<IncomeActivity> activityReference;

        public UpdateVM(IncomeActivity context){
            activityReference = new WeakReference<>(context);
        }
        public void updateVal(Income myModel)
        {
            incomeVm.insertData(myModel);
        }

        @Override
        protected Boolean doInBackground(Income... myModels) {
            updateVal(myModels[0]);
            return true;
        }
    }
}
