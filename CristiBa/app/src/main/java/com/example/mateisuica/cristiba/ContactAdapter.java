package com.example.mateisuica.cristiba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mateisuica on 28/03/16.
 */
public class ContactAdapter extends ArrayAdapter {

    private List<String> names;

    public ContactAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        names = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = vi.inflate(R.layout.contact_row, parent, false);
        TextView name = (TextView)rootView.findViewById(R.id.contactName);
        name.setText(names.get(position));

        return rootView;
    }

    @Override
    public int getCount() {
        return names.size();
    }
}
