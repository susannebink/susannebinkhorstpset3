package com.example.susanne.susannebinkhorst_pset3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DishActivity extends AppCompatActivity {

    ImageView image;
    RequestQueue queue;
    String dish_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        Intent intent = getIntent();
        dish_selected = intent.getStringExtra("dish");

        final TextView dish_name = findViewById(R.id.dish);
        final TextView dish_info = findViewById(R.id.dish_info);
        final TextView price = findViewById(R.id.price);

        queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/menu";


        // Request a string response from the provided URL.
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");

                            for (int i = 0; i < items.length(); i++) {

                                JSONObject item = items.getJSONObject(i);

                                String dish = item.getString("name");
                                if (dish.equals(dish_selected))
                                {
                                    String image_url = item.getString("image_url");
                                    dish_name.setText(dish);
                                    price.setText("$ " + item.getString("price"));
                                    dish_info.setText(item.getString("description"));
                                    RequestImage(image_url);
                                }

                            }


                        } catch (JSONException e){
                            dish_name.setText("that didn't work");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dish_name.setText("That didn't work!");
            }
        });
        queue.add(request);

    }

    public void RequestImage(String image_url){
        ImageRequest imageRequest = new ImageRequest(image_url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                image = findViewById(R.id.image);
                image.setImageBitmap(response);
            }
        }, 0, 0, null, Bitmap.Config.ALPHA_8, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DishActivity.this, "Image not found", Toast.LENGTH_SHORT).show();
            }
        }
    );
        queue.add(imageRequest);
    }

    public void add_item(View view) {

        Intent intent =  new Intent(DishActivity.this, OrderActivity.class);
        String dish = dish_selected + " is added to your order";
        intent.putExtra("myDish", dish_selected);

        Toast.makeText(DishActivity.this, dish, Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("dish", dish_selected);
//    }
//
//    public void onRestoreInstanceState(Bundle inState){
//        super.onRestoreInstanceState(inState);
//        dish_selected = inState.getString("dish");
//    }

    public void ToOrder(View view) {
        Intent intent = new Intent(DishActivity.this, OrderActivity.class);
        intent.putExtra("myDish", " ");
        startActivity(intent);
    }

    public void ShowMenu(View view) {
        Intent intent = new Intent(DishActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
