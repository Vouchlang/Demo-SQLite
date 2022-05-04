package com.example.sqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sqlite.Models.Contact;
import com.example.sqlite.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapterContact extends ArrayAdapter<Contact> {

    ArrayList<Contact> arrContact;
    Context context;
    LayoutInflater layoutInflater;

    public CustomAdapterContact(Context context, ArrayList<Contact> arrContact){
        super(context, R.layout.list_item, arrContact);

        this.context = context;
        this.arrContact = arrContact;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        if(convertView == null){
            row = layoutInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView getName = row.findViewById(R.id.getName);
        TextView getEmail = row.findViewById(R.id.getEmail);
        TextView getAddress = row.findViewById(R.id.getAddress);

        Contact c = arrContact.get(position);
        getName.setText(c.getName());
        getEmail.setText(c.getEmail());
        getAddress.setText(c.getAddress());

        return row;
    }
}
