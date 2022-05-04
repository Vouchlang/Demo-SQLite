package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.Database.DatabaseAdapter;
import com.example.sqlite.Models.Contact;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    EditText updateID, updateName, updateEmail, updateAddress;
    Button btnUpdate, btnDelete;
    DatabaseAdapter databaseAdapter;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        databaseAdapter = new DatabaseAdapter(this);
        ID = getIntent().getStringExtra("id");

        Toast.makeText(this, "ID >>> " + ID, Toast.LENGTH_SHORT).show();

        updateID = findViewById(R.id.updateID);
        updateName = findViewById(R.id.updateName);
        updateEmail = findViewById(R.id.updateEmail);
        updateAddress = findViewById(R.id.updateAddress);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        databaseAdapter.openDB();
        Contact c = databaseAdapter.getContactByID(Integer.parseInt(ID));
        databaseAdapter.closeDB();

        updateID.setText(ID);
        updateName.setText(c.getName());
        updateEmail.setText(c.getEmail());
        updateAddress.setText(c.getAddress());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contact contact = new Contact(ID, updateName.getText().toString(), updateEmail.getText().toString(), updateAddress.getText().toString());

                databaseAdapter.openDB();
                int rowEffect = databaseAdapter.updateContact(contact);
                databaseAdapter.closeDB();

                Toast.makeText(UpdateActivity.this, "Updated Row >>> " + rowEffect, Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int rowEffect;
                databaseAdapter.openDB();
                rowEffect = databaseAdapter.deleteContact(Integer.parseInt(ID));
                databaseAdapter.closeDB();

                Toast.makeText(UpdateActivity.this, "Deleted Row >>> " + rowEffect, Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }
}