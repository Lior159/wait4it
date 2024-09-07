package com.example.wait4it.Webview;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wait4it.R;
import com.example.wait4it.Utilities.AuthUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NewsMainPage extends AppCompatActivity {
    private static final String HOST_URL = "https://wait4it.azurewebsites.net";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main_page);

        WebView webView = (WebView) findViewById(R.id.news_WEBVIEW_webView);
        String jwtToken = AuthUtil.getJwtToken(NewsMainPage.this);
        String username = AuthUtil.getUsername(NewsMainPage.this);

        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
//                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });


        String url = String.format("%s/news?username=%s&token=%s", HOST_URL, username, jwtToken);
        webView.loadUrl(url);

    }
}