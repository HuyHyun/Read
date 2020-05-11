package com.example.read.Models;

import android.net.Uri;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebViewClient extends WebViewClient {
    String link;

    public CustomWebViewClient(String link) {
        this.link = link;
    }

    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        // Do something with the event here
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().equals(link)) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }

        // reject anything other
        return true;
    }
}
