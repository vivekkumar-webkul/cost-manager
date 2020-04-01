package com.example.costmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Management extends AppCompatActivity {

    protected EditText username, oldPassword, password, repassword;
    protected Button updateButton;
    protected DatabaseHelper db;
    protected Cursor cursor;
    protected int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        username = (EditText) findViewById(R.id.update_username);
        oldPassword = (EditText) findViewById(R.id.old_password);
        password = (EditText) findViewById(R.id.update_password);
        repassword = (EditText) findViewById(R.id.update_repassword);

        updateButton = (Button) findViewById(R.id.update_button);

        try{
            SharedPreferences sh = getSharedPreferences("CostManagerSharedPrefereces", MODE_PRIVATE);
            userId = sh.getInt("userId",0);
            db = new DatabaseHelper(Management.this);
            cursor = db.getUserData(userId);
            cursor.moveToFirst();
            username.setText(cursor.getString(cursor.getColumnIndex("name")));
//            username.setText("test value");
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty()){
                    Toast.makeText(Management.this, "Please Enter Username.", Toast.LENGTH_SHORT).show();
                    return;
                } else if(oldPassword.getText().toString().isEmpty()) {
                    Toast.makeText(Management.this, "Please Enter Old Password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = oldPassword.getText().toString();
                Boolean exists = pwd.equals(cursor.getString(cursor.getColumnIndex("password"))) ? true : false;
                if(!exists) {
                    Toast.makeText(Management.this, "Invalid Old Password! Try Again.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.getText().toString().equals(repassword.getText().toString()) || password.getText().toString().isEmpty()) {
                    Toast.makeText(Management.this, "Invalid Password! Try Again.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    String newPwd = password.getText().toString();
                    db.updatePasswordAndName(newPwd,username.getText().toString(), userId);
                    Toast.makeText(Management.this, "Update Successfull.", Toast.LENGTH_SHORT).show();
                    oldPassword.setText("");
                    password.setText("");
                    repassword.setText("");

                    startActivity(new Intent(Management.this, MainActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
