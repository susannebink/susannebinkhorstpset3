package com.example.susanne.susannebinkhorst_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    String myDish;
    Integer Dish1 = 0;
    Integer Dish2 = 0;
    Integer Dish3 = 0;
    Integer Dish4 = 0;
    Integer Dish5 = 0;
    Integer Dish6 = 0;
    String Dish_name1 = "Spaghetti and Meatballs";
    String Dish_name2 = "Margherita Pizza";
    String Dish_name3 = "Grilled Steelhead Trout Sandwich";
    String Dish_name4 = "Pesto Linguini";
    String Dish_name5 = "Chicken Noodle Soup";
    String Dish_name6 = "Italian Salad";
    ListView list;
    List<String> myArray = new ArrayList<String>();
    ArrayAdapter<String> myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        LoadFromSharedPrefs();

        Intent intent = getIntent();
        myDish = intent.getStringExtra("myDish");


        if (myDish.equals(Dish_name1)){
            Dish1 += 1;
        }
        if (myDish.equals(Dish_name2)){
            Dish2 += 1;
        }
        if (myDish.equals(Dish_name3)){
            Dish3 += 1;

        }
        if (myDish.equals(Dish_name4)){
            Dish4 += 1;
        }
        if (myDish.equals(Dish_name5)){
            Dish5 += 1;
        }
        if (myDish.equals(Dish_name6)){
            Dish6 += 1;

        }


        if (Dish1 != 0 ){
            myArray.add(Dish1 + "x " + Dish_name1);
        }
        if (Dish2 != 0){
            myArray.add(Dish2  + "x " + Dish_name2);
        }
        if (Dish3 != 0){
            myArray.add(Dish3  + "x " + Dish_name3);
        }
        if (Dish4 != 0){
            myArray.add(Dish4 + "x " + Dish_name4);
        }
        if (Dish5 != 0){
            myArray.add(Dish5  + "x " + Dish_name5);
        }
        if (Dish6 != 0){
            myArray.add(Dish6  + "x " + Dish_name6);
        }

        list = findViewById(R.id.list);
        myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myArray);

        list.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        SaveToSharedPrefs();
    }

    public void AddMoreToOrder(View view){
        list.setLongClickable(false);
        list.setLongClickable(true);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapter, View v,
                                           int index, long arg3) {
                String item_clicked = list.getItemAtPosition(index).toString();

                if (item_clicked.endsWith(Dish_name1)){
                    Dish1 += 1;

                }
                if (item_clicked.endsWith(Dish_name2)){
                    Dish2 += 1;

                }
                if (item_clicked.endsWith(Dish_name3)){
                    Dish3 += 1;
                }
                if (item_clicked.endsWith(Dish_name4)){
                    Dish4 += 1;
                }
                if (item_clicked.endsWith(Dish_name5)){
                    Dish5 += 1;

                }
                if (item_clicked.endsWith(Dish_name6)){
                    Dish6 += 1;

                }

                myArray.clear();

                if (Dish1 != 0 ){
                    myArray.add(Dish1 + "x " + Dish_name1);
                }
                if (Dish2 != 0){
                    myArray.add(Dish2  + "x " + Dish_name2);
                }
                if (Dish3 != 0){
                    myArray.add(Dish3  + "x " + Dish_name3);
                }
                if (Dish4 != 0){
                    myArray.add(Dish4 + "x " + Dish_name4);
                }
                if (Dish5 != 0){
                    myArray.add(Dish5  + "x " + Dish_name5);
                }
                if (Dish6 != 0){
                    myArray.add(Dish6  + "x " + Dish_name6);
                }

                SaveToSharedPrefs();

                Toast.makeText(OrderActivity.this, "Item added to your order",
                        Toast.LENGTH_LONG).show();

                myAdapter.notifyDataSetChanged();

                return true;
            }

        });

    }

    public void DeleteFromOrder(View view){
        list.setLongClickable(false);
        list.setLongClickable(true);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapter, View v,
                                           int index, long arg3) {
                String item_clicked = list.getItemAtPosition(index).toString();

                if (item_clicked.endsWith(Dish_name1)){
                    Dish1 -= 1;
                }
                if (item_clicked.endsWith(Dish_name2)){
                    Dish2 -= 1;

                }
                if (item_clicked.endsWith(Dish_name3)){
                    Dish3 -= 1;
                }
                if (item_clicked.endsWith(Dish_name4)){
                    Dish4 -= 1;

                }
                if (item_clicked.endsWith(Dish_name5)){
                    Dish5 -= 1;

                }
                if (item_clicked.endsWith(Dish_name6)){
                    Dish6 -= 1;

                }

                myArray.clear();

                if (Dish1 != 0 ){
                    myArray.add(Dish1 + "x " + Dish_name1);
                }
                if (Dish2 != 0){
                    myArray.add(Dish2  + "x " + Dish_name2);
                }
                if (Dish3 != 0){
                    myArray.add(Dish3  + "x " + Dish_name3);
                }
                if (Dish4 != 0){
                    myArray.add(Dish4 + "x " + Dish_name4);
                }
                if (Dish5 != 0){
                    myArray.add(Dish5  + "x " + Dish_name5);
                }
                if (Dish6 != 0){
                    myArray.add(Dish6  + "x " + Dish_name6);
                }
                Toast.makeText(OrderActivity.this, "Item deleted from your order",
                        Toast.LENGTH_LONG).show();
                SaveToSharedPrefs();

                myAdapter.notifyDataSetChanged();

                return true;
            }
        });


    }

    public void EditOrder(View view) {
        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        Button cancel = findViewById(R.id.cancel);

        TextView textView = findViewById(R.id.textView2);

        textView.setText("Please select if you want to delete or add and select your dish");
        add.setText("Add");
        delete.setText("Delete");
        cancel.setText("Cancel");
    }

    public void SubmitOrder(View view) {
        Intent intent = new Intent(OrderActivity.this, SubmitActivity.class);
        intent.putExtra("dish1_name", Dish_name1);
        intent.putExtra("dish2_name", Dish_name2);
        intent.putExtra("dish3_name", Dish_name3);
        intent.putExtra("dish4_name", Dish_name4);
        intent.putExtra("dish5_name", Dish_name5);
        intent.putExtra("dish6_name", Dish_name6);

        intent.putExtra("dish1", Dish1);
        intent.putExtra("dish2", Dish2);
        intent.putExtra("dish3", Dish3);
        intent.putExtra("dish4", Dish4);
        intent.putExtra("dish5", Dish5);
        intent.putExtra("dish6", Dish6);
        finish();
        startActivity(intent);
    }

    public void ShowMenu(View view) {
        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void SaveToSharedPrefs(){
        SharedPreferences preferences = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("dish1_number", Dish1);
        editor.putInt("dish2_number", Dish2);
        editor.putInt("dish3_number", Dish3);
        editor.putInt("dish4_number", Dish4);
        editor.putInt("dish5_number", Dish5);
        editor.putInt("dish6_number", Dish6);

        editor.commit();
    }

    public void LoadFromSharedPrefs(){
        SharedPreferences preferences = this.getSharedPreferences("settings", this.MODE_PRIVATE);

        Dish1  = preferences.getInt("dish1_number", 0);
        Dish2 = preferences.getInt("dish2_number", 0);
        Dish3 = preferences.getInt("dish3_number", 0);
        Dish4 = preferences.getInt("dish4_number", 0);
        Dish6 = preferences.getInt("dish5_number", 0);
        Dish6 = preferences.getInt("dish6_number", 0);

    }

    public void CancelEdit(View view) {
        list.setLongClickable(false);
        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        Button cancel = findViewById(R.id.cancel);

        TextView textView = findViewById(R.id.textView2);
        myAdapter.notifyDataSetChanged();
        textView.setText("Your Order");
        add.setText("");
        delete.setText("");
        cancel.setText("");
    }
}
