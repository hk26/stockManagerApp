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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Profit_calculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    EditText priceProfit;
    Button calcButton;
    TextView answerProfit;
    int gPosition;

    StockListActivity stockListActivity = new StockListActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_calculator);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Profit Calculator</font>"));

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        priceProfit = findViewById(R.id.Price);
        calcButton = findViewById(R.id.pbutton);
        answerProfit = findViewById(R.id.answer);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,stockListActivity.stockNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nProfit = Integer.parseInt(priceProfit.getText().toString());
                int value = stockListActivity.profitCounter.get(gPosition);

                answerProfit.setText("Profit: "+String.valueOf(nProfit*value));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gPosition = position;
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}