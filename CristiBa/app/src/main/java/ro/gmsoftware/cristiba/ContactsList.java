package ro.gmsoftware.cristiba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String whereClause = "email = '" + mList.get(position).getSecondPerson() + "'";
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);
                Backendless.Persistence.of(BackendlessUser.class).find(dataQuery, new AsyncCallback<BackendlessCollection<BackendlessUser>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<BackendlessUser> response) {

                        BackendlessUser user = response.getData().get(0);
                        DeliveryOptions deliveryOptions = new DeliveryOptions();

                        deliveryOptions.addPushSinglecast( (String)user.getProperty("gcmToken") );

                        PublishOptions publishOptions = new PublishOptions();
                        publishOptions.putHeader( "android-ticker-text", "Ba, Cristi!" );
                        publishOptions.putHeader( "android-content-title", "Cheama-l ba pe Cristi, ba!" );
                        publishOptions.putHeader( "android-content-text",  user.getEmail() + ", te cheama! Baaa, Cristii!" );

                        Backendless.Messaging.publish("Cristiii!", publishOptions, deliveryOptions, new AsyncCallback<MessageStatus>() {
                            @Override
                            public void handleResponse(MessageStatus response) {
                                Toast.makeText(ContactsList.this, "L-am chemat pe Cristi!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(ContactsList.this, "Cristi nu-i acasa!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(ContactsList.this, "Cristi nu-i acasa!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        final BackendlessUser currentUser = Backendless.UserService.CurrentUser();

        String whereClause = "firstPerson = '" + currentUser.getEmail() + "'";
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);
        Backendless.Persistence.of(Contact.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Contact>>() {
            @Override
            public void handleResponse(BackendlessCollection<Contact> response) {
                mList = response.getData();
                contacts.setAdapter(new ContactAdapter(ContactsList.this, R.layout.contact_row, response.getData()));
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(ContactsList.this, "Niciun Cristi nu-i acasa!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_contact:
                Intent intent = new Intent(this, AddContactActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
