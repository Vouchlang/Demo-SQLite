package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sqlite.Database.DatabaseAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText inputName, inputEmail, inputAddress;
    Button btnSave, btnList;
    DatabaseAdapter databaseAdapter;
//    String name, email, address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        public void onClick(View view){
//            PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
//        }

        databaseAdapter = new DatabaseAdapter(this);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputAddress = findViewById(R.id.inputAddress);
        btnSave = findViewById(R.id.btnSave);
        btnList = findViewById(R.id.btnList);

        btnSave.setOnClickListener(this);
        btnList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:

                String name, email, address;
                long rowEffect;

                name = inputName.getText().toString();
                email = inputEmail.getText().toString();
                address = inputAddress.getText().toString();

                databaseAdapter.openDB();
                rowEffect = databaseAdapter.insertContact(name, address, email);
                databaseAdapter.closeDB();

                Toast.makeText(this, "Is Inserted >>> " + rowEffect, Toast.LENGTH_SHORT).show();

                finish();

                break;
            default:

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                break;
        }
    }
}