package com.example.shareoption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

private ShareActionProvider shareActionProvider;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_option:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String link="https://play.google.com/store/apps/details?id=in.becandid.app.becandid";
         intent.putExtra(Intent.EXTRA_TEXT,link);
         startActivity(Intent.createChooser(intent,"share via"));
                break;
        }
        Toast.makeText(MainActivity.this,"share app",Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}
