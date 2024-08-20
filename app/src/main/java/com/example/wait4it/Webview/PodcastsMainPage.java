package com.example.wait4it.Webview;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wait4it.R;
import com.example.wait4it.Utilities.AuthUtil;

public class PodcastsMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcasts_main_page);

        WebView webView = (WebView) findViewById(R.id.podcasts_WEBVIEW_webView);

        String jwtToken = AuthUtil.getJwtToken(PodcastsMainPage.this);
        String username = AuthUtil.getUsername(PodcastsMainPage.this);

        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
//                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });


        String url = String.format("http://192.168.7.7:8000/podcasts?username=%s&token=%s", username, jwtToken);
        webView.loadUrl(url);
    }
}