package com.example.wait4it.Webview;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wait4it.R;
import com.example.wait4it.Utilities.AuthUtil;

public class ArticlesMainPage extends AppCompatActivity {

    private static final String HOST_URL = "https://wait4it.azurewebsites.net";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_articles_main_page);

        webView = (WebView) findViewById(R.id.articles_WEBVIEW_webView);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Your custom behavior here
                if (!shouldInterceptBackPress()) {
                    // Default behavior (calls the system back press)
                    if (isEnabled()) {
                        setEnabled(false);
                        onBackPressed();  // Or use super.onBackPressed()
                    }
                }
            }
        });

        String jwtToken = AuthUtil.getJwtToken(ArticlesMainPage.this);
        String username = AuthUtil.getUsername(ArticlesMainPage.this);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
//                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });

        String url = String.format("%s/articles?username=%s&token=%s", HOST_URL, username, jwtToken);
        webView.loadUrl(url);
    }

    private boolean shouldInterceptBackPress() {
        if (webView.canGoBack()){
            webView.goBack();
            return true;
        }
        // Custom logic to decide whether to intercept the back press
        return webView.canGoBack();
    }
}