package com.example.tourguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText userId, password, name;
    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = userId.getText().toString();
                String pass = password.getText().toString();
                String n = name.getText().toString();

                //Test

                if (UserValidationService.isValidName(n) && UserValidationService.isValidPassword(pass) && UserValidationService.isValidUserId(user)) {


                    //Creating User Entity
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUserId(userId.getText().toString());
                    userEntity.setPassword(password.getText().toString());
                    userEntity.setName(name.getText().toString());

                    if (validateInput(userEntity)) {
                        //Do insert operation
                        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                        UserDao userDao = userDatabase.userDao();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //Register User
                                userDao.registerUser(userEntity);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String infomessage = getString(R.string.message);
                                        Toast.makeText(getApplicationContext(), infomessage, Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).start();

                    } else {
                        String infomessage2 = getString(R.string.message2);
                        Toast.makeText(getApplicationContext(), infomessage2, Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Your data is not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getName().isEmpty() ||
            userEntity.getPassword().isEmpty() ||
            userEntity.getName().isEmpty()) {
            return false;
        }
        return true;
    }
}