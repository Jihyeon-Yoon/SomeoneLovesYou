package com.ssy.graduationwork.someonelovesyou;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class CopyrightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyright);

        // 타이틀바 가운데 정렬
        getSupportActionBar().setDisplayOptions(android.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        // 저작권 정보를 WebView를 이용해서 보여줄 것임.
        WebView mWebView = findViewById(R.id.wv_liberal_arts);

        // assets 폴더 안의 www 폴더 안에 html파일이 있음. 그 html 파일이 저작권 정보를 보여주는 html 파일임.
        mWebView.loadUrl("file:///android_asset/www/copyright.html");

    }
}
