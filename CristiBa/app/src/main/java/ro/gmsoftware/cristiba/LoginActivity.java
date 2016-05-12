package ro.gmsoftware.cristiba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                String usernameString = username.getText().toString().trim();
                String passwordString = password.getText().toString().trim();

                Backendless.UserService.login( usernameString, passwordString, new AsyncCallback<BackendlessUser>()
                {
                    public void handleResponse( BackendlessUser user )
                    {
                        user.setProperty("gcmToken", CristiApp.token);
                        Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                            }
                        });
                        // user has been logged in
                        Intent intent = new Intent(LoginActivity.this, ContactsList.class);
                        startActivity(intent);
                    }

                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(LoginActivity.this, "Ai gresit parola jupane!", Toast.LENGTH_SHORT).show();
                        Log.e("LOGIN", fault.getMessage());
                        // login failed, to get the error code call fault.getCode()
                    }
                });

            }
        });
    }
}
