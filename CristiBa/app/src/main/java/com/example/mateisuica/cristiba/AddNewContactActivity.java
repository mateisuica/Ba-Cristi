package com.example.mateisuica.cristiba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

public class AddNewContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        final EditText email = (EditText)findViewById(R.id.email);
        Button add = (Button)findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForUserAndAdd(email.getText().toString().trim());
            }
        });
    }

    private void checkForUserAndAdd(final String email) {
        BackendlessDataQuery query = new BackendlessDataQuery("email = '" + email + "'");

        Backendless.Persistence.of(BackendlessUser.class).find(query, new AsyncCallback<BackendlessCollection<BackendlessUser>>() {

            @Override
            public void handleResponse(BackendlessCollection<BackendlessUser> response) {
                if (response != null && response.getData() != null && response.getData().size() > 0) {
                    Contact contact = new Contact();
                    contact.setFirstPerson(Backendless.UserService.CurrentUser().getEmail());
                    contact.setSecondPerson(email);
                    Backendless.Persistence.of(Contact.class).save(contact, new AsyncCallback<Contact>() {
                        @Override
                        public void handleResponse(Contact response) {
                            Toast.makeText(AddNewContactActivity.this, "Contact added successful!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(AddNewContactActivity.this, "Contact could not be added!", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Toast.makeText(AddNewContactActivity.this, "Contact could not be added!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(AddNewContactActivity.this, "Contact could not be added!", Toast.LENGTH_SHORT).show();
                Log.d("ADD", "Add contact failed for " + fault.getMessage());
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
    }
}
