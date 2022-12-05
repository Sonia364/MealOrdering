package com.sonia.mealordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spinner;
    EditText price;
    EditText total;
    SeekBar seekbar;
    double tip = 0;
    RadioGroup radio_group;
    RadioButton  radioBtn;
    ImageView foodImg;

    int quantity = 1;
    final int TAX = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price = findViewById(R.id.mealPrice);
        total = findViewById(R.id.totalPrice);
        spinner = findViewById(R.id.mealsList);
        seekbar = findViewById(R.id.quantitySeekbar);
        radio_group = findViewById(R.id.radioGroup);
        foodImg = findViewById(R.id.foodImg);
        findViewById(R.id.radio_10).setOnClickListener(this);
        findViewById(R.id.radio_15).setOnClickListener(this);
        findViewById(R.id.radio_20).setOnClickListener(this);

        radio_group.setOnClickListener(
                v->{
                    int selectedId= radio_group.getCheckedRadioButtonId();
                    radioBtn = findViewById(selectedId);
                    Toast.makeText(MainActivity.this,radioBtn.getText(),Toast.LENGTH_SHORT).show();
                }
        );

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                quantity = progressChangedValue;
                calculatePrice();
//                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        String selectedItem = String.valueOf(adapterView.getItemAtPosition(pos));
        String priceVal = "10";
        switch (selectedItem){
            case "Subway":
                priceVal = "10";
                foodImg.setImageResource(R.drawable.subway);
                break;
            case "Taco":
                priceVal = "20";
                foodImg.setImageResource(R.drawable.taco);
                break;
            case "Banana":
                priceVal = "10";
                foodImg.setImageResource(R.drawable.banana);
                break;
            case "Burger":
                priceVal = "25";
                foodImg.setImageResource(R.drawable.burger);
                break;
            case "Pizza":
                priceVal = "15";
                foodImg.setImageResource(R.drawable.pizza);
                break;
            case "Orange":
                priceVal = "15";
                foodImg.setImageResource(R.drawable.orange);
                break;
            case "Fries":
                priceVal = "35";
                foodImg.setImageResource(R.drawable.fries);
                break;
            case "Apple":
                priceVal = "30";
                foodImg.setImageResource(R.drawable.apple);
                break;
            case "Papaya":
                priceVal = "25";
                foodImg.setImageResource(R.drawable.papaya);
                break;
            case "Lemon":
                priceVal = "15";
                foodImg.setImageResource(R.drawable.lemon);
                break;

        }
        price.setText(priceVal);
        calculatePrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_10:
                tip = 10;
                break;
            case R.id.radio_15:
                tip = 15;
                break;
            case R.id.radio_20:
                tip = 20;
                break;
            default:
                tip = 0;
        }
        calculatePrice();
    }

    public void calculatePrice(){
        double amount = quantity * Double.parseDouble(String.valueOf(price.getText().toString()));
        double taxAmount = (amount * TAX)/100;
        double tipAmount =  (amount * tip)/100;
        double finalAmount = amount + taxAmount + tipAmount;
        total.setText(String.valueOf(finalAmount));
    }

}