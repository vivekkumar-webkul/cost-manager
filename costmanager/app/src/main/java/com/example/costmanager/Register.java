package com.example.costmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    protected EditText username, email, password, repassword;

    protected Button registerButton;
    protected DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.register_username);
        email = (EditText) findViewById(R.id.register_email);
        password = (EditText) findViewById(R.id.register_password);
        repassword = (EditText) findViewById(R.id.register_repassword);
        registerButton = (Button) findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String uName = username.getText().toString().trim();
                String uEmail = email.getText().toString().trim();
                String uPassword = password.getText().toString().trim();
                String uRepassword = repassword.getText().toString().trim();

                if(uPassword.equals(uRepassword)) {
                    long result = db.addUser(uName, uEmail, uPassword);

                    if(result > 0) {
                        Toast.makeText(Register.this, "Register Successfully.", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(Register.this, Login.class);
                        startActivity(loginIntent);
                    } else {
                        Toast.makeText(Register.this, "Error! Please Try Again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Password not match", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
