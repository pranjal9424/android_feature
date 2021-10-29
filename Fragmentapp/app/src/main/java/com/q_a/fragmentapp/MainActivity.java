package com.q_a.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.q_a.fragmentapp.Fragment.FirstFragment;
import com.q_a.fragmentapp.Fragment.SecondBlankFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button frag1=findViewById(R.id.fragment1);
        Button frag2=findViewById(R.id.fragment2);
        //LinearLayout linearLayout=findViewById(R.id.linearFragment);

        FirstFragment firstFragment=new FirstFragment();
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linearFragment,firstFragment);
        transaction.commit();

        frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstFragment firstFragment=new FirstFragment();
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearFragment,firstFragment);
                transaction.commit();
            }
        });

        frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondBlankFragment secondBlankFragment=new SecondBlankFragment();
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearFragment,secondBlankFragment);
                transaction.commit();
            }
        });
    }
}