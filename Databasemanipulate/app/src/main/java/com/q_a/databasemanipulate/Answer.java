package com.q_a.databasemanipulate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Answer extends AppCompatActivity {
    TextView ques,ans;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        ques = findViewById(R.id.textquest);
        ans  = findViewById(R.id.texxtanswer);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        String pranjal = Show.listItems.get(position).getAnswer();

        ques.setText(Show.listItems.get(position).getQuestion());
        ans.setText(pranjal);
    }
}