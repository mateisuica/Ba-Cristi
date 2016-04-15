package com.example.mateisuica.cristiba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = (EditText)findViewById(R.id.email);
        final EditText password = (EditText)findViewById(R.id.password);
        Button register = (Button)findViewById(R.id.loginButton);

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               registerUser(email.getText().toString(), password.getText().toString());
           }
       });
    }

    private void registerUser(final String email, final String password) {
        String pass = "";
        for(int i = 0; i < password.length(); i++) {
            pass += "*";
        }
        Toast.makeText(this, "user registered with email: " + email + " and password: " + pass, Toast.LENGTH_LONG).show();
        ;
    }

}
