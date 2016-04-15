package com.example.mateisuica.cristiba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        Button login = (Button)findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String usernameString = username.getText().toString().trim();
                 final String passwordString = password.getText().toString().trim();

                Backendless.UserService.login(usernameString,
                        passwordString,
                        new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                                response.setProperty("gcmToken",  CristiBaApp.token);
                                Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                                    @Override
                                    public void handleResponse(BackendlessUser response) {

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                    }
                                });
                                Intent intent = new Intent(LoginActivity.this, ContactsList.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(LoginActivity.this, "Login failed: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("LOGIN", "login failed for " + usernameString);
                            }
                        });
            }
        });
    }

    private boolean isValid(String user, String pass) {
        return user.equals(pass);
    }
}
