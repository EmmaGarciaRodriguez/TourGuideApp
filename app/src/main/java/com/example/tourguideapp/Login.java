package com.example.tourguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userId, password;
    Button login;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()) {
                    String infomessage2 = getString(R.string.message2);
                    Toast.makeText(getApplicationContext(), infomessage2, Toast.LENGTH_SHORT).show();
                } else{
                    // Perform Query
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String infomessage3 = getString(R.string.message3);
                                        Toast.makeText(getApplicationContext(), infomessage3, Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(getApplicationContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                String name = userEntity.name;
                                String password = userEntity.password;
                                String userId = userEntity.userId;
                                Intent intent = new Intent(Login.this, HomeScreen.class);
                                intent.putExtra("name", name);
                                intent.putExtra("password", password);
                                intent.putExtra("userId", userId);
                                startActivity(intent);
                            }
                        }
                    }).start();
                }
            }
        });
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}