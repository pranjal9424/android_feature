package Interview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.q_a.companycode.Listitem_Name;
import com.q_a.companycode.MainActivity;
import com.q_a.companycode.MyAdapter_Name;
import com.q_a.companycode.QuestionActivity;
import com.q_a.companycode.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InterviewActivity extends AppCompatActivity {
    ProgressBar progressBar;
    private ListView listView;
    private MyAdapter_subject_name adapter;
    public static ArrayList<Listitem_subject_name> listItems = new ArrayList<>();
    public static final String  url ="https://compulsory-rug.000webhostapp.com/InterviesSubjectName.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        listView = (ListView) findViewById(R.id.recycleview);
        adapter = new MyAdapter_subject_name(listItems,this);




        /////////////////progress bar
        progressBar=findViewById(R.id.load);

        ////////////////////next activity set postion
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                listItems.get(position).getSubject();

                startActivity(new Intent(getApplicationContext(), InterviewQuestionActivity.class)
                        .putExtra("position",position));

            }
        });
        retrieveData();
    }

    public void retrieveData(){
        progressBar.setVisibility(View.VISIBLE); //to show
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
                                Listitem_subject_name item = new Listitem_subject_name(
                                        o.getString("subject"),
                                        o.getString("number"),
                                        o.getString("image"));
                                listItems.add(item);

                            }

                            adapter = new MyAdapter_subject_name(listItems, getApplicationContext());
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
                Toast.makeText(InterviewActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}