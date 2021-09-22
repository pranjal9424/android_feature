package com.q_a.companycode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CodeActivity extends AppCompatActivity {
    TextView ans;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        ans  = findViewById(R.id.textCode);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        String pranjal = QuestionActivity.listItems.get(position).getAnswer();


        ans.setText(pranjal);
    }
}