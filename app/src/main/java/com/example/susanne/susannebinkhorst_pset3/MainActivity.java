package com.example.susanne.susannebinkhorst_pset3;

import android.app.Activity;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> myArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myArray);
        list.setAdapter(adapter);


        myArray.clear();

        final TextView textView = findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/categories";


        // Request a string response from the provided URL.
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray categories = response.getJSONArray("categories");

                            for (int i = 0; i < categories.length(); i++) {

                                myArray.add(categories.getString(i));
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
        // Add the request to the RequestQueue.
        queue.add(request);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter,View view, int position, long l){
                String category = "You selected " + String.valueOf(adapter.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, category, Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("category", String.valueOf(adapter.getItemAtPosition(position)));
                startActivity(intent);
            }
        });
        }

    public void ToOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("myDish", " ");
        startActivity(intent);
    }

}


