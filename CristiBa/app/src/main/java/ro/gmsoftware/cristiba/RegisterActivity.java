package ro.gmsoftware.cristiba;

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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = (EditText)findViewById(R.id.email);
        final EditText password = (EditText)findViewById(R.id.password);
        Button register = (Button)findViewById(R.id.registerButton);

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               registerUser(email.getText().toString(), password.getText().toString());
           }
       });
    }

    private void registerUser(final String email, final String password) {
        BackendlessUser user = new BackendlessUser();
        user.setProperty("email", email.trim());
        user.setPassword(password.trim());

        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {

            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(RegisterActivity.this, "Registration has failed", Toast.LENGTH_SHORT).show();
                Log.e("REGISTRATION", fault.getMessage());
            }
        });
    }

}
