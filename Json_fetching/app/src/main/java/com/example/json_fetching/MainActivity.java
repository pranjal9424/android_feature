package com.example.json_fetching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
TextView testIdText,twitterFeedText,feedDateText,addressText,emailText;
RequestQueue rq;
String url="https://demo0923422.mockable.io/api/getDetails";
String testId,twitterFeed,feedDate,address,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 ////Volley reques uin this page
        rq= Volley.newRequestQueue(this);
      /// calling
        
        testIdText=(TextView)findViewById(R.id.testid);
        feedDateText=(TextView)findViewById(R.id.feedDate);
        twitterFeedText=(TextView)findViewById(R.id.twitterFeed);
        addressText=(TextView)findViewById(R.id.address);
        emailText=(TextView)findViewById(R.id.email);
        sendjsonrequest();
        
    }
    public  void sendjsonrequest(){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    testId=response.getString("testId");
                    twitterFeed=response.getString("twitterFeed");
                    feedDate=response.getString("feedDate");
                    address=response.getString("address");
                    email=response.getString("email");



                    testIdText.setText(testId);
                    twitterFeedText.setText(twitterFeed);
                    feedDateText.setText(feedDate);
                    addressText.setText(address);
                    emailText.setText(email);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(jsonObjectRequest);
    }

}



