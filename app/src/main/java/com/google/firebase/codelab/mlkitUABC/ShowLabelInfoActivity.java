package com.google.firebase.codelab.mlkitUABC;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;
import com.google.firebase.codelab.labelScannerUABC.ProductListActivity;
import com.google.firebase.codelab.labelScannerUABC.R;

public class ShowLabelInfoActivity extends AppCompatActivity {

    private WebView webView;
    private WebAppInterface webAppInterface;
    private FoodItem foodItem;

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_label_info);

        webView = findViewById(R.id.asl_webview);
        webAppInterface = new WebAppInterface(this);

        //webView.loadUrl("http://health-app.conisoft.org/html/showUserProducts.html");
        webView.loadUrl("file:////android_asset/WebViewContent/html/showLabelInfo.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(webAppInterface, "Android");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }
    }
}