package com.q_a.unityadver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class MainActivity extends AppCompatActivity {
    private String unityGameID = "4373671";
    private Boolean testMode = true;
    private String banerId="baner";
    private String interId="intertial_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare a new listener:
        final UnityAdsListener myAdsListener = new UnityAdsListener();
        // Add the listener to the SDK:
        UnityAds.addListener(myAdsListener);
        // Initialize the SDK:
        UnityAds.initialize(this, unityGameID, testMode);


        Button ban=findViewById(R.id.banner);
        Button inter=findViewById(R.id.inter);

        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnityAds.load(interId);
                display();
            }
        });
        ////////for bannner
        IUnityBannerListener iUnityBannerListener= new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                ((ViewGroup)findViewById(R.id.bannerAds)).removeView(view);
                ((ViewGroup)findViewById(R.id.bannerAds)).addView(view);
            }

            @Override
            public void onUnityBannerUnloaded(String s) {

            }

            @Override
            public void onUnityBannerShow(String s) {

            }

            @Override
            public void onUnityBannerClick(String s) {

            }

            @Override
            public void onUnityBannerHide(String s) {

            }

            @Override
            public void onUnityBannerError(String s) {

            }
        };
        UnityBanners.setBannerListener(iUnityBannerListener);

        Button banner=findViewById(R.id.banner);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnityBanners.loadBanner(MainActivity.this,banerId);
            }
        });

    }

    private void display() {
        if(UnityAds.isReady(interId)){
            UnityAds.show(MainActivity.this,interId);
        }
    }

    // Implement the IUnityAdsListener interface methods:
    private class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady(String adUnitId) {
            // Implement functionality for an ad being ready to show.
            Toast.makeText(MainActivity.this,"iner load",Toast.LENGTH_SHORT);
        }

        @Override
        public void onUnityAdsStart(String adUnitId) {
            // Implement functionality for a user starting to watch an ad.
        }

        @Override
        public void onUnityAdsFinish(String adUnitId, UnityAds.FinishState finishState) {
            // Implement functionality for a user finishing an ad.
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            // Implement functionality for a Unity Ads service error occurring.
        }
    }
}