package com.example.mateisuica.cristiba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        final EditText email = (EditText)findViewById(R.id.email);
        Button add = (Button)findViewById(R.id.addButton);

        final BackendlessUser currentUser = Backendless.UserService.CurrentUser();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send to server
                if(!TextUtils.isEmpty(email.getText().toString())) {
                    Contact contact = new Contact();
                    contact.setFirstPerson(currentUser.getEmail());
                    contact.setSecondPerson(email.getText().toString());
                    Backendless.Persistence.save(contact, new AsyncCallback<Contact>() {
                        @Override
                        public void handleResponse(Contact response) {
                            Toast.makeText(AddContactActivity.this, "Contact added successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(AddContactActivity.this, "Cannot add contact due to an error!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
