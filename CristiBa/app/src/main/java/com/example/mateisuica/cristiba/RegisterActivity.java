package com.example.korjikk.cristiba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText username= (EditText)findViewById(R.id.username);
        final EditText password= (EditText)findViewById(R.id.password);
        final EditText repeatPassword= (EditText)findViewById(R.id.repeatPassword);
        Button login= (Button)findViewById(R.id.registerButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TODO
                       citesce username,password & repeated password
                       valideaza ca pass=repeatPass si pass>4 caractere;
                       da mesaj de eroare in caz de esec,
                       else afiseaza mesaj de succes
                 */
                String usernameString=username.getText().toString();
                String passwordString=password.getText().toString();
                String repeatPasswordString=repeatPassword.getText().toString();

                boolean usernameAndPasswordWereValidated= isValid(usernameString, passwordString, repeatPasswordString);
                if(usernameAndPasswordWereValidated) {
                    Toast.makeText(RegisterActivity.this, "Succes!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password too short or does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isValid(String user, String pass,String repeatPass) {
        return (pass.equals(repeatPass) && pass.length()>3);
    }
}
