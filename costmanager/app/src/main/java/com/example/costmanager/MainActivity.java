package com.example.costmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        editor.commit();

        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
    }
}
