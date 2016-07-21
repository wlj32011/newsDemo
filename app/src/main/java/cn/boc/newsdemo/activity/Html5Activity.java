package cn.boc.newsdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import cn.boc.newsdemo.R;

public class Html5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html5);

        WebView webView = (WebView) findViewById(R.id.webview);

        webView.loadUrl("file:///android_asset/index.html");
    }
}
