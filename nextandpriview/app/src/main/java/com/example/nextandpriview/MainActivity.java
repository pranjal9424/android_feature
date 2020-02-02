package com.example.nextandpriview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView judu;
    TextView isi;
    String title[] = {"A Fox and A Crow","A lion and A rabbit", "Benevolent jumbo","Brakes Fail"};
    String title2[] = {"Mughal empire","Akbar life summary","the croen in the kingdom", "wise birbal?"};
    int halmann=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        judu=(TextView)findViewById(R.id.judul);
        isi=(TextView)findViewById(R.id.isi);

        judu.setText(title[halmann]);
        isi.setText(title2[halmann]);
    }
    public void Next(View view){
        if(halmann < 3) {
            halmann++;
            judu.setText(title[halmann]);
            isi.setText(title2[halmann]);
        }
    }

    public void Prev(View view){
        if(halmann > 0) {
            halmann--;
            judu.setText(title[halmann]);
            isi.setText(title2[halmann]);
        }
    }
}
