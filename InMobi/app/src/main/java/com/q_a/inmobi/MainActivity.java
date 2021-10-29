package com.q_a.inmobi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RelativeLayout;

import com.inmobi.ads.AdMetaInfo;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.sdk.InMobiSdk;
import com.inmobi.sdk.SdkInitializationListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //InMobiSdk.init(getApplicationContext(), String);
        InMobiBanner bannerAd = (InMobiBanner)findViewById(R.id.banner);

        //InMobiBanner bannerAd = new InMobiBanner(MainActivity.this, 1468078426600L);

        bannerAd.load();

    }

}