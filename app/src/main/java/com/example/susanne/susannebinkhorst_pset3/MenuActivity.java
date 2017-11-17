package com.example.susanne.susannebinkhorst_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    List<String> myArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        final String category = intent.getStringExtra("category");

        ListView list = findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myArray);
        list.setAdapter(adapter);


        myArray.clear();

        final TextView textView = findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/menu";
        if (category.equals("entrees")){
            textView.setText("Entrees");
        }
        if (category.equals("appetizers")){
            textView.setText("Appetizers");
        }


        // Request a string response from the provided URL.
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");

                            for (int i = 0; i < items.length(); i++) {

                                JSONObject item = items.getJSONObject(i);
                                String dish = item.getString("category");
                                if (dish.equals(category))
                                {
                                    myArray.add(item.getString("name"));
                                }

                            }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e){
                            textView.setText("that didn't work");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        queue.add(request);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l){

                Intent intent =  new Intent(MenuActivity.this, DishActivity.class);
                intent.putExtra("dish", String.valueOf(adapter.getItemAtPosition(position)));
                startActivity(intent);
            }
        });
    }

    public void ShowMenu(View view) {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void ToOrder(View view) {
        Intent intent = new Intent(MenuActivity.this, OrderActivity.class);
        intent.putExtra("myDish", " ");
        startActivity(intent);
    }
}

