package com.openclassrooms.realestatemanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMain;
    private TextView textViewQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BUG :textViewMain was pointed on a resource of the SecondActivity
        this.textViewMain = findViewById(R.id.activity_main_activity_text_view_main);
        this.textViewQuantity = findViewById(R.id.activity_main_activity_text_view_quantity);

        this.configureTextViewMain();
        this.configureTextViewQuantity();
    }

    private void configureTextViewMain(){
        this.textViewMain.setTextSize(15);
        this.textViewMain.setText("Le premier bien immobilier enregistré vaut ");
    }

    private void configureTextViewQuantity(){
        /*BUG :Convert int quantity to String quantity to work
        Quantity is an int or we need to put a string in setText(String)*/
        String quantity = Integer.toString(Utils.convertDollarToEuro(100));
        this.textViewQuantity.setTextSize(20);

        this.textViewQuantity.setText(quantity);
    }
}
