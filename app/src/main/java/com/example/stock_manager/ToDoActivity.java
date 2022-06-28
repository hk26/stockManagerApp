package com.example.stock_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemAdapter;
    private ListView listView;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        listView = findViewById(R.id.list_todo);
        addButton = findViewById(R.id.add_button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>ToDo List</font>"));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
            }
        });
        database d = new database(this);
        items = new ArrayList<String>();

        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemAdapter);
        deleteItems();
    }

    private void deleteItems() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), items.get(position).toString()+" REMOVED!", Toast.LENGTH_SHORT).show();

                items.remove(position);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem(View v) {
        EditText input = findViewById(R.id.todoContent);
        String itemText = input.getText().toString();
        new ModelClass(itemText);
        if(!itemText.trim().equals("")) {
            itemAdapter.add(itemText);
            input.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}