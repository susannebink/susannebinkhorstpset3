package com.example.susanne.susannebinkhorst_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitActivity extends AppCompatActivity {

    RequestQueue queue;
    List<String> myArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Intent intent = getIntent();
        String dish1_name = intent.getStringExtra("dish1_name");
        String dish2_name = intent.getStringExtra("dish2_name");
        String dish3_name = intent.getStringExtra("dish3_name");
        String dish4_name = intent.getStringExtra("dish4_name");
        String dish5_name = intent.getStringExtra("dish5_name");
        String dish6_name = intent.getStringExtra("dish6_name");

        Integer dish1 = intent.getIntExtra("dish1", 0);
        Integer dish2 = intent.getIntExtra("dish2", 0);
        Integer dish3 = intent.getIntExtra("dish3", 0);
        Integer dish4 = intent.getIntExtra("dish4", 0);
        Integer dish5 = intent.getIntExtra("dish5", 0);
        Integer dish6 = intent.getIntExtra("dish6", 0);

        if (dish1 != 0 ){
            myArray.add(dish1 + "x " + dish1_name);
        }
        if (dish2 != 0){
            myArray.add(dish2  + "x " + dish2_name);
        }
        if (dish3 != 0){
            myArray.add(dish3  + "x " + dish3_name);
        }
        if (dish4 != 0){
            myArray.add(dish4 + "x " + dish4_name);
        }
        if (dish5 != 0){
            myArray.add(dish5  + "x " + dish5_name);
        }
        if (dish6 != 0){
            myArray.add(dish6  + "x " + dish6_name);
        }


        final TextView text = findViewById(R.id.textView5);

        queue = Volley.newRequestQueue(this);

        ListView list = findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myArray);

        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        String url = "https://resto.mprog.nl/order";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String minutes = response.getString("preparation_time");
                            text.setText("Estimated waiting time is " + minutes + " minutes");
                        } catch (JSONException e){
                            text.setText("that didn't work");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text.setText("That didn't work!");
            }
        });
        queue.add(request);

        SaveToSharedPrefs();
    }

    public void SaveToSharedPrefs(){
        SharedPreferences preferences = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("dish1_number", 0);
        editor.putInt("dish2_number", 0);
        editor.putInt("dish3_number", 0);
        editor.putInt("dish4_number", 0);
        editor.putInt("dish5_number", 0);
        editor.putInt("dish6_number", 0);

        editor.commit();
    }

    public void ShowMenu(View view) {
        Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
