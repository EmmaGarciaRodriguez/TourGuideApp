package com.example.tourguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    UserDatabase userDatabase;
    UserEntity currentUser;
    HomeScreenActivity home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String user = intent.getStringExtra("user");
        String password = intent.getStringExtra("password");

        EditText n = findViewById(R.id.editName);
        EditText u = findViewById(R.id.editUser);
        EditText p = findViewById(R.id.editPassword);

        n.setText(name);
        u.setText(user);
        p.setText(password);


        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        UserDao userDao = userDatabase.userDao();

        Button btn = findViewById(R.id.savebutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = n.getText().toString();
                String newPassword = p.getText().toString();
                String newUser = u.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        currentUser = userDao.getCurrentUser(user);
                        // Actualizar los datos existentes en la entidad UserEntity con los nuevos datos ingresados por el usuario
                        currentUser.setName(newName);
                        currentUser.setUserId(newUser);
                        currentUser.setPassword(newPassword);

                        // Guardar los cambios actualizados en Room
                        new Thread(() -> {
                            userDatabase.userDao().updateUser(currentUser);
                        }).start();
                    }
                }).start();


                if (savedInstanceState == null) {
                    // Si no hay ningún fragmento en el contenedor, creamos una instancia de FirstFragment y lo agregamos
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frame_container, new FirstFragment(home))
                            .commit();

                }

            }


        });
        Button btnrtn = findViewById(R.id.rtnButton);
        btnrtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); //Return method
            }
        });
    }
}