package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();

        createMap();
    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Ryzen");
        spinnerArrayList.add("Intel");
        spinnerArrayList.add("Elbrus");
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Ryzen", 61990.0);
        goodsMap.put("Intel", 67000.0);
        goodsMap.put("Elbrus", 102990.0);
    }


    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityText = findViewById(R.id.quantityText);
        quantityText.setText(Integer.toString(quantity));
        TextView priceRoubles = findViewById(R.id.priceRubles);
        priceRoubles.setText("" + quantity * price);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView quantityText = findViewById(R.id.quantityText);
        quantityText.setText(Integer.toString(quantity));
        TextView priceRoubles = findViewById(R.id.priceRubles);
        priceRoubles.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceRoubles = findViewById(R.id.priceRubles);
        priceRoubles.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName) {
            case "Ryzen":
                goodsImageView.setImageResource(R.drawable.ryzen);
                break;
            case "Intel":
                goodsImageView.setImageResource(R.drawable.intel);
                break;
            case "Elbrus":
                goodsImageView.setImageResource(R.drawable.elbrus);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.ryzen);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;
        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        startActivity(orderIntent);

    }
}
