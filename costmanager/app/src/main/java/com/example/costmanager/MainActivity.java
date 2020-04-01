package com.example.costmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.costmanager.Expense.ExpenseActivity;
import com.example.costmanager.Income.IncomeActivity;

public class MainActivity extends AppCompatActivity {

    private Button income;
    private Button expense;
    private Button stat;
    private Button accManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sh = getSharedPreferences("CostManagerSharedPrefereces", MODE_PRIVATE);
        sh.getInt("userId",0);
//        int userId = getSharedPreferences("userId",MODE_PRIVATE);
        //getIntent().getIntExtra("userId",0);
        Log.d("seeeeeeet =------ ",Integer.toString(sh.getInt("userId",0)));
        setContentView(R.layout.activity_main);
        initButtons();
    }

    public void initButtons(){
        income = findViewById(R.id.income);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(intent);
            }
        });
        expense = findViewById(R.id.expense);
        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseActivity.class);
                startActivity(intent);
            }
        });
        stat = findViewById(R.id.stats);
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(intent);
            }
        });
        accManagement = findViewById(R.id.acc_mgmt);
        accManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Management.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sign_out:
                signOutUser();
                Toast.makeText(MainActivity.this, "Sign out Successfully", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOutUser() {
        SharedPreferences sh = getSharedPreferences("CostManagerSharedPrefereces", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString("username", "");
        editor.putInt("userId",0);
        editor.commit();

        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
    }
}
