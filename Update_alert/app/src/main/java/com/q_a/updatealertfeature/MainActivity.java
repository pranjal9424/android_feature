package com.q_a.updatealertfeature;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tvCurrentVersion,tvLatestVersion;
    String sCurrentVersion,sLatestVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Assign variable
        tvCurrentVersion=findViewById(R.id.tv_current_version);
        tvLatestVersion=findViewById(R.id.tv_latest_version);

        //Get latest version from play store
        new GetLatestVersion().execute();
    }

    private class GetLatestVersion extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            try{
                sLatestVersion= Jsoup
                        .connect("https://play.google.com/store/apps/details?id="
                        + getPackageName())
                        .timeout(30000)
                        .get()
                        .select("div.hAyfc:nth-child(4)>"+
                                "span:nth-child(2) > div:nth-child(1)"+
                                "> span:nth-child(1)")
                        .first()
                        .ownText();
            }catch (IOException e){
                e.printStackTrace();
            }
            return sLatestVersion;
        }

        @Override
        protected void onPostExecute(String s) {
            // Get current version
            sCurrentVersion=BuildConfig.VERSION_NAME;
            tvCurrentVersion.setText(sCurrentVersion);
            tvLatestVersion.setText(sLatestVersion);

            if(sLatestVersion!=null){
                float cVersion=Float.parseFloat(sCurrentVersion);
                float lVersion=Float.parseFloat(sLatestVersion);

                if(lVersion > cVersion){
                    updateAlertDialog();
                }
            }

        }
    }

    private void updateAlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage("Update Available");
        builder.setCancelable(false);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id="+getPackageName())));
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }
}