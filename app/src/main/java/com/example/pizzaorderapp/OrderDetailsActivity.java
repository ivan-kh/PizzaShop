package com.example.pizzaorderapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        TextView textViewPizzaName = findViewById(R.id.textViewPizzaName);
        TextView textViewPizzaSize = findViewById(R.id.textViewPizzaSize);
        TextView textViewPizzaPrice = findViewById(R.id.textViewPizzaPrice);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        Button buttonSubmitOrder = findViewById(R.id.buttonSubmitOrder);

        String pizzaName = getIntent().getStringExtra("PIZZA_NAME");
        String pizzaSize = getIntent().getStringExtra("PIZZA_SIZE");
        int pizzaPrice = getIntent().getIntExtra("PIZZA_PRICE", 0);
        boolean extraCheese = getIntent().getBooleanExtra("EXTRA_CHEESE", false);
        boolean extraMeat = getIntent().getBooleanExtra("EXTRA_MEAT", false);
        boolean extraVeggies = getIntent().getBooleanExtra("EXTRA_VEGGIES", false);

        textViewPizzaName.setText(pizzaName);
        textViewPizzaSize.setText(pizzaSize);
        textViewPizzaPrice.setText(String.valueOf(pizzaPrice) + " грн");

        if (extraCheese) {
            textViewPizzaPrice.append("\nДодатковий сир");
        }
        if (extraMeat) {
            textViewPizzaPrice.append("\nДодаткове м'ясо");
        }
        if (extraVeggies) {
            textViewPizzaPrice.append("\nДодаткові овочі");
        }

        buttonSubmitOrder.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String address = editTextAddress.getText().toString();
            String phone = editTextPhone.getText().toString();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone)) {
                Toast.makeText(OrderDetailsActivity.this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show();
            } else {
                if (!phone.matches("\\d+")) {
                    Toast.makeText(OrderDetailsActivity.this, "Номер телефону повинен містити тільки цифри", Toast.LENGTH_SHORT).show();
                } else {
                    String orderSummary = "Замовлення: " + pizzaName + "\nРозмір: " + pizzaSize + "\nЦіна: " + pizzaPrice + " грн\nІм'я: " + name + "\nАдреса: " + address + "\nТелефон: " + phone;
                    Toast.makeText(OrderDetailsActivity.this, orderSummary, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
