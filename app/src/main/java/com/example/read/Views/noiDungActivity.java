package com.example.read.Views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.read.Models.CustomWebViewClient;
import com.example.read.Models.MainActivity;
import com.example.read.R;
import com.example.read.Services.TinTuc;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import static com.example.read.Models.MainActivity.database;
import static com.example.read.Views.User_Fragment.checkLogin;

public class noiDungActivity extends AppCompatActivity {

    ProgressBar load;
    ShareDialog shareDialog;
    ImageButton btnShare;
    ShareLinkContent shareLinkContent;
    ImageButton btnLuu;
    ImageButton btnBack;
    boolean flag;
    WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung);

        shareDialog = new ShareDialog(noiDungActivity.this);
        btnShare = (ImageButton) findViewById(R.id.btnShare);

        load = (ProgressBar) findViewById(R.id.webload);
        load.setVisibility(View.VISIBLE);
        btnLuu = (ImageButton) findViewById(R.id.btnLuu);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        final TinTuc tinTuc = (TinTuc) intent.getSerializableExtra("dulieu");
        final String link = tinTuc.getLink();


        if (checkDanhDau(link) == 1) {
            btnLuu.setImageResource(R.drawable.bookmarkluuroi);
            flag = true;
        } else {
            btnLuu.setImageResource(R.drawable.bookmarkluu);
            flag = false;
        }


        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    flag = true;
                    btnLuu.setImageResource(R.drawable.bookmarkluuroi);
                    checkDanhDau(link);
                    MainActivity.database.INSERT_Data("danhdau", tinTuc);
                    if (DanhDauActivity.adapter != null) {
                        DanhDauActivity.GetDataDanhDau();
                    }
                } else {
                    flag = false;
                    btnLuu.setImageResource(R.drawable.bookmarkluu);
                    database.QueryData("DELETE FROM danhdau WHERE Link = '" + link + "'");
                    if (DanhDauActivity.adapter != null) {
                        DanhDauActivity.GetDataDanhDau();
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        webView = (WebView) findViewById(R.id.web);
        webView.loadUrl(link);
        if (webView.isShown()) {
            load.setVisibility(View.INVISIBLE);
        }
        webView.setWebViewClient(new CustomWebViewClient(link));
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            webSettings.setForceDark(WebSettings.FORCE_DARK_ON);
        }

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLogin) {
                    if (shareDialog.canShow(ShareLinkContent.class)) {
                        shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse(link)).build();
                    }
                    shareDialog.show(shareLinkContent);
                } else
                    Toast.makeText(noiDungActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public int checkDanhDau(String link) {
        Cursor cursor = database.GetData("SELECT COUNT(*) FROM danhdau WHERE Link = '" + link + "'");
        return (cursor.moveToFirst()) ? cursor.getInt(0) : 0;

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else
            finish();
    }


}
