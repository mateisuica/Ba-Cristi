package com.example.mateisuica.cristiba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.DeliveryOptions;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.List;

public class ContactsList extends AppCompatActivity {

    private List<Contact> mList;
    private ListView contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        contacts = (ListView) findViewById(R.id.contactsList);

    }

    private void getData(final ListView contacts) {
        BackendlessDataQuery query = new BackendlessDataQuery("firstPerson = '" + Backendless.UserService.CurrentUser().getEmail() + "'");

        Backendless.Persistence.of(Contact.class).find(query, new AsyncCallback<BackendlessCollection<Contact>>() {


            @Override
            public void handleResponse(BackendlessCollection<Contact> response) {
                if (response != null && response.getData() != null && contacts != null) {
                    mList = response.getData();
                    contacts.setAdapter(new ContactAdapter(ContactsList.this, R.layout.contact_row, mList));
                    contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Contact contact = mList.get(position);
                            BackendlessDataQuery query = new BackendlessDataQuery("email = '" + contact.getSecondPerson() + "'");

                            Backendless.Persistence.of(BackendlessUser.class).find(query, new AsyncCallback<BackendlessCollection<BackendlessUser>>() {

                                @Override
                                public void handleResponse(BackendlessCollection<BackendlessUser> response) {
                                    BackendlessUser contact1 = response.getData().get(0);
                                    DeliveryOptions deliveryOptions = new DeliveryOptions();
                                    deliveryOptions.addPushSinglecast( (String)contact1.getProperty("gcmToken") );

                                    PublishOptions publishOptions = new PublishOptions();
                                    publishOptions.putHeader( "android-ticker-text", "Ba Cristi!" );
                                    publishOptions.putHeader( "android-content-title", "Cristiii, ba Cristi!" );
                                    publishOptions.putHeader( "android-content-text", "Cheama-l ba pe Cristi, ba!" );

                                    Backendless.Messaging.publish("This message was scheduled 20 sec ago", publishOptions, deliveryOptions, new AsyncCallback<MessageStatus>() {
                                        @Override
                                        public void handleResponse(MessageStatus response) {

                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {

                                        }
                                    });
                                }

                                @Override
                                        public void handleFault(BackendlessFault fault) {
                                            Toast.makeText(ContactsList.this, "Contact not found!", Toast.LENGTH_SHORT).show();

                                        }
                                    });

                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(contacts);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacts_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_contact:
                addNewContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNewContact() {
        Intent intent = new Intent(this, AddNewContactActivity.class);
        startActivity(intent);
    }
}
