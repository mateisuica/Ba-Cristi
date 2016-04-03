package com.example.korjikk.cristiba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username= (EditText)findViewById(R.id.username);
        final EditText password= (EditText)findViewById(R.id.password);
        Button login= (Button)findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TODO
                       citesce username & password
                       le valideaza
                       da mesaj de eroare in caz de esec
                       merge la urmatorul ecran in caz de succes
                 */
                String usernameString=username.getText().toString();
                String passwordString=password.getText().toString();

                boolean usernameAndPasswordWereValidated= isValid(usernameString, passwordString);
                if(usernameAndPasswordWereValidated==true) {
                    Intent intent=new Intent(LoginActivity.this,ContactsList.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LoginActivity.this, "Wrong username and/or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isValid(String user, String pass) {
        return user.equals(pass);
    }
}
