package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText inputCity;
    Button add_button, delete_button, confirm_button;
    int selected_position = -1;


    public void add(android.view.View view){
        //add city input to list
        inputCity.setVisibility(View.VISIBLE);
        confirm_button.setVisibility(View.VISIBLE);
    }

    public void delete(android.view.View view){
        //delete city input from list
        if (selected_position != -1) {
            dataList.remove(selected_position);
            cityAdapter.notifyDataSetChanged();
            selected_position = -1;
        } else {
            Toast.makeText(this, "Select a city to delete", Toast.LENGTH_SHORT).show();
        }
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        inputCity = findViewById(R.id.city_input);
        add_button = findViewById(R.id.add_button);
        delete_button = findViewById(R.id.delete_button);
        confirm_button = findViewById(R.id.confirm_button);

        inputCity.setVisibility(View.GONE);
        confirm_button.setVisibility(View.GONE);






        String[] cities = {"Edmonton", "Calgary", "Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selected_position = position;
            Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });


        // Add button shows input field
        add_button.setOnClickListener(this::add);

        // Delete city
        delete_button.setOnClickListener(this::delete);

        confirm_button.setOnClickListener(v -> {
            String city = inputCity.getText().toString().trim();
            if (!city.isEmpty()) {
                dataList.add(city);
                cityAdapter.notifyDataSetChanged();
                inputCity.setText("");
                inputCity.setVisibility(View.GONE);
                confirm_button.setVisibility(View.GONE);
            } else {
                Toast.makeText(MainActivity.this, "Enter a city name", Toast.LENGTH_SHORT).show();
            }
        });








    }
}
















