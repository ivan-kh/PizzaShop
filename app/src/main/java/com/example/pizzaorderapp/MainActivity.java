package com.example.pizzaorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupPizza, radioGroupSize;
    private CheckBox checkBoxCheese, checkBoxMeat, checkBoxVeggies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupPizza = findViewById(R.id.radioGroupPizza);
        radioGroupSize = findViewById(R.id.radioGroupSize);
        checkBoxCheese = findViewById(R.id.checkBoxCheese);
        checkBoxMeat = findViewById(R.id.checkBoxMeat);
        checkBoxVeggies = findViewById(R.id.checkBoxVeggies);
        Button buttonOrder = findViewById(R.id.buttonOrder);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderActivity();
            }
        });
    }

    private void openOrderActivity() {
        Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);

        int selectedPizzaId = radioGroupPizza.getCheckedRadioButtonId();
        String pizzaName = "";
        int pizzaBasePrice = 0;

        if (selectedPizzaId == R.id.radioPizza1) {
            pizzaName = "Піцца Моцарелла";
            pizzaBasePrice = 150;
        } else if (selectedPizzaId == R.id.radioPizza2) {
            pizzaName = "Піцца Гаваї";
            pizzaBasePrice = 120;
        } else if (selectedPizzaId == R.id.radioPizza3) {
            pizzaName = "Піцца Грибна";
            pizzaBasePrice = 170;
        }

        int selectedSizeId = radioGroupSize.getCheckedRadioButtonId();
        String size = "";
        int sizePrice = 0;

        if (selectedSizeId == R.id.radioSmall) {
            size = "Маленька";
            sizePrice = 0;
        } else if (selectedSizeId == R.id.radioMedium) {
            size = "Середня";
            sizePrice = 20;
        } else if (selectedSizeId == R.id.radioLarge) {
            size = "Велика";
            sizePrice = 50;
        }

        int totalPrice = pizzaBasePrice + sizePrice;

        if (checkBoxCheese.isChecked()) {
            totalPrice += 20;
        }
        if (checkBoxMeat.isChecked()) {
            totalPrice += 30;
        }
        if (checkBoxVeggies.isChecked()) {
            totalPrice += 15;
        }

        intent.putExtra("PIZZA_NAME", pizzaName);
        intent.putExtra("PIZZA_SIZE", size);
        intent.putExtra("PIZZA_PRICE", totalPrice);
        intent.putExtra("EXTRA_CHEESE", checkBoxCheese.isChecked());
        intent.putExtra("EXTRA_MEAT", checkBoxMeat.isChecked());
        intent.putExtra("EXTRA_VEGGIES", checkBoxVeggies.isChecked());

        startActivity(intent);
    }
}
