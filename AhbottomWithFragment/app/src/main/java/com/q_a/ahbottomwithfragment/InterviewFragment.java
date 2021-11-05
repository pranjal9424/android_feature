package com.q_a.ahbottomwithfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class InterviewFragment extends Fragment {

    public InterviewFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private ListView listView;
    public MyAdapter_intervies adapter;
    public static ArrayList<Listitem_question> listItems = new ArrayList<>();
    public  static final String url ="https://pranjal9424.online/LCP/InterviewQuestion.php";
    int position;
    TextView cn;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_interview, container, false);

        listView = (ListView) view.findViewById(R.id.recycleview);
        adapter = new MyAdapter_intervies(listItems,getActivity());

        ///////////////////////////////////////////
        progressBar=view.findViewById(R.id.load);

        retrieveData();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String question=listItems.get(position).getQuestion();
                String answer=listItems.get(position).getAnswer();
                //ShowAnswer(question,answer);

            }
        });

        return view;
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

                            adapter = new MyAdapter_intervies(listItems, getContext());
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
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE );
            }
        }){
            protected Map<String,String> getParams(){
                Map<String, String > param=new HashMap<>();
                param.put("param","c");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
}