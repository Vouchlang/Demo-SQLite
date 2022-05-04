package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.Adapter.CustomAdapterContact;
import com.example.sqlite.Database.DatabaseAdapter;
import com.example.sqlite.Models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    DatabaseAdapter databaseAdapter;
    ArrayList<Contact> arrContact;
    CustomAdapterContact customAdapterContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        databaseAdapter = new DatabaseAdapter(this);

        lv = findViewById(R.id.lv);
        
        registerForContextMenu(lv);

        //Database
        databaseAdapter.openDB();
        arrContact = databaseAdapter.getContacts();
        databaseAdapter.closeDB();

        //List with Adapter
        customAdapterContact = new CustomAdapterContact(this, arrContact);
        lv.setAdapter(customAdapterContact);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact c;
                String id;
                c = customAdapterContact.getItem(i);
                id = c.getId();

                Toast.makeText(ListActivity.this, "Clicked >>> " + id, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListActivity.this, UpdateActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnAdd:
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert!!!");
                builder.setMessage("Do you want to delete this User?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.dismiss();

                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}