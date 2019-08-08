package com.example.a9792.meragrocery;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;
    private ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView=(WebView)findViewById(R.id.meraagrocery);
        mProgressBar=(ProgressBar)findViewById(R.id.progressbar);



        WebSettings webSettings=myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://meragrocery.com/");
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                //starting page of url given or currrent url
                mProgressBar.setVisibility(View.VISIBLE);
                setTitle("Loading....");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                //ending part of given url it's means proccesbar are end with this section
                mProgressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());
                super.onPageFinished(view, url);
            }
        });


    }
    @Override
    public void onBackPressed(){
        if ((myWebView.canGoBack())) {

            myWebView.goBack();
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();

        }else
            super.onBackPressed();


    }
}
