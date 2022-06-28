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

public class StockListActivity extends AppCompatActivity {

    public static ArrayList<String> stockNames = new ArrayList<>();
    public static ArrayList<String> stockCounts = new ArrayList<>();
    private ArrayAdapter<String> stockNamesAdapter;
    private ArrayAdapter<String> stockCountsAdapter;
    private ListView itemListNames, itemListNumbers;
    private Button addButton;
    private EditText name, counter;
    public static ArrayList<Integer> profitCounter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);

        itemListNames = findViewById(R.id.item_list_names);
        itemListNumbers = findViewById(R.id.item_list_numbers);
        addButton = findViewById(R.id.add_item_button);
        name = findViewById(R.id.add_item_edittext);
        counter = findViewById(R.id.add_item_editnumber);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Stock List</font>"));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
                //stocks++;
            }
        });

        stockNamesAdapter = new ArrayAdapter<>(this, R.layout.single_stock_layout, R.id.item_name, stockNames);
        stockCountsAdapter = new ArrayAdapter<>(this, R.layout.single_row_numbers, R.id.item_number, stockCounts);

        itemListNames.setAdapter(stockNamesAdapter);
        itemListNumbers.setAdapter(stockCountsAdapter);

        deleteItems();
    }

    private void addItem(View v) {

        String txtName = name.getText().toString().trim();
        String numCounter = counter.getText().toString().trim();

        if(!txtName.trim().equals("") && !numCounter.trim().equals("")) {

            int index = stockNames.indexOf(txtName);
            if(index!=-1) {
                stockCounts.set(index,numCounter);
                stockCountsAdapter.notifyDataSetChanged();
            }
            else {
                stockNamesAdapter.add(txtName);
                stockCountsAdapter.add(numCounter);
            }
            name.setText("");
            counter.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter text and number properly!", Toast.LENGTH_SHORT).show();
        }
        for(int i=0; i<stockNames.size(); i++) {
            profitCounter.add(0);
        }
    }

    private void deleteItems() {
        itemListNames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Item removed", Toast.LENGTH_SHORT).show();

                stockNames.remove(position);
                stockCounts.remove(position);
                profitCounter.remove(position);
                stockNamesAdapter.notifyDataSetChanged();
                stockCountsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void clickPlus(View view) {

        int position = itemListNumbers.getPositionForView(view);
        int updated = Integer.parseInt(stockCounts.get(position));
        updated+=1;
        stockCounts.set(position,String.valueOf(updated));
        stockCountsAdapter.notifyDataSetChanged();
    }

    public void clickMinus(View view) {

        int position = itemListNumbers.getPositionForView(view);
        int updated = Integer.parseInt(stockCounts.get(position));
        updated-=1;
        stockCounts.set(position,String.valueOf(updated));
        stockCountsAdapter.notifyDataSetChanged();

        int n=profitCounter.get(position);
        ++n;
        profitCounter.set(position,n);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}