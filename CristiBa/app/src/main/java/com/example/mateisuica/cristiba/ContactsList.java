package com.example.mateisuica.cristiba;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        List<String> list = new ArrayList<>();
        list.add("Roxana");
        list.add("Ioana");
        list.add("Ionela");
        list.add("Matei");
        list.add("Andrei");
        list.add("Ion");
        list.add("Gigi");
        list.add("Becali");
        list.add("Zoli");
        list.add("Dior");
        list.add("Andrei");
        list.add("Gigi");
        list.add("Becali");
        list.add("Zoli");
        list.add("Dior");
        list.add("Andrei");
        list.add("Ion");
        list.add("Gigi");
        list.add("Becali");
        list.add("Zoli");
        list.add("Dior");

        ListView contacts = (ListView) findViewById(R.id.contactsList);
        contacts.setAdapter(new ContactAdapter(ContactsList.this, R.layout.contact_row, list));
        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayer mediaPlayer = MediaPlayer.create(ContactsList.this, R.raw.ba_cristi);
                mediaPlayer.start();
            }
        });

    }
}
