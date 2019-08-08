package com.example.java_connectivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
        SwipeRefreshLayout mSwipeRefreshLayout;
public static final String URI_DATA="http://subjiwala.org/quiz_androidinter_java.php";
private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private List<ListItem> listItems;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {

@Override
public void run() {

        mSwipeRefreshLayout.setRefreshing(true);


        loadrecyclerviewdata();
        }
        });
        }
@Override
public void onRefresh() {

        loadrecyclerviewdata();


        }

private void loadrecyclerviewdata() {
        mSwipeRefreshLayout.setRefreshing(true);
        /*final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
        URI_DATA,
        new Response.Listener<String>() {
@Override
public void onResponse(String response) {

        //  progressDialog.dismiss();

        try {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("result");

        for (int i =0;i<jsonArray.length();i++){

        JSONObject o = jsonArray.getJSONObject(i);
        ListItem item = new ListItem(
        o.getString("question"),
        o.getString("answer"));
        listItems.add(item);

        }

        adapter = new MyAdapter(listItems, getApplicationContext());
        recyclerView.setAdapter(adapter);


        } catch (JSONException e) {

        }
        mSwipeRefreshLayout.setRefreshing(false);

        }
        },
        new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {

        //progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);

        }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        }
        }
