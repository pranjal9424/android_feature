package com.q_a.ahbottomwithfragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Map;

public class InterviewQuestionActivity extends AppCompatActivity {
    private ListView listView;
    private MyAdapter_intervies adapter;
    public static ArrayList<Listitem_question> listItems = new ArrayList<>();
    public  static final String url ="https://pranjal9424.online/LCP/InterviewQuestion.php";
    int position;
    TextView cn;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_question);



        listView = (ListView) findViewById(R.id.recycleview);
        adapter = new MyAdapter_intervies(listItems,this);

        ///////////////////////////////////////////
        progressBar=findViewById(R.id.load);

        retrieveData();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String question=listItems.get(position).getQuestion();
                String answer=listItems.get(position).getAnswer();
                ShowAnswer(question,answer);

            }
        });
    }

    public void retrieveData(){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listItems.clear();

                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");

                            for (int i =0;i<jsonArray.length();i++){

                                JSONObject o = jsonArray.getJSONObject(i);
                                Listitem_question item = new Listitem_question(
                                        o.getString("question"),
                                        o.getString("answer"));
                                listItems.add(item);

                            }

                            adapter = new MyAdapter_intervies(listItems, getApplicationContext());
                            listView.setAdapter(adapter);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InterviewQuestionActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE );
            }
        }){
            protected Map<String,String> getParams(){
                Map<String, String > param=new HashMap<>();
                param.put("param","c");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void ShowAnswer(String question,String answer){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(question);
        builder.setMessage(answer);
        builder.setCancelable(false);





        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }
}