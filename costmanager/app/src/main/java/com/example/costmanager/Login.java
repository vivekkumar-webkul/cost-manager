package com.example.costmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    protected EditText username;
    protected EditText password;
    protected Button loginButton;
    protected DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Cursor res = db.checkUser(user, pwd);
                res.moveToFirst();
                Boolean exists = res.getCount() > 0 ? true : false;
                if(exists) {
                    int userId = res.getInt(0);
                    SharedPreferences sh = getSharedPreferences("CostManagerSharedPrefereces", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sh.edit();
                    editor.putInt("userId", userId);
                    editor.putString("username", user);
                    editor.commit();

                    Toast.makeText(Login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    Intent mainActivityIntent = new Intent(Login.this, MainActivity.class);
//                    mainActivityIntent.putExtra("userId", userId);
                    startActivity(mainActivityIntent);
                } else {
                    Toast.makeText(Login.this, "Login Failed! Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registerActivity(View view) {
        Intent registerActivityIntent = new Intent(this, Register.class);
        startActivity(registerActivityIntent);
    }
}
