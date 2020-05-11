package com.example.read.Models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.read.Database.Database;
import com.example.read.R;
import com.example.read.Views.Home_Fragment;
import com.example.read.Views.Search_Fragment;
import com.example.read.Views.User_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static Database database;
    Fragment home = new Home_Fragment();
    Fragment search = new Search_Fragment();
    Fragment user = new User_Fragment();
    Fragment active = home;
    FragmentManager fm = getSupportFragmentManager();


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "QuanLyTinTuc.sqlite", null, 2);
        database.QueryData("CREATE TABLE IF NOT EXISTS lichsu(Id INTEGER PRIMARY KEY AUTOINCREMENT,Tieude VARCHAR(255), Hinhanh VARCHAR(255), Link VARCHAR(255) UNIQUE, Data VARCHAR(50))");
        database.QueryData("CREATE TABLE IF NOT EXISTS danhdau(Id INTEGER PRIMARY KEY AUTOINCREMENT,Tieude VARCHAR(255), Hinhanh VARCHAR(255), Link VARCHAR(255) UNIQUE, Data VARCHAR(50))");
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.botom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        fm.beginTransaction().add(R.id.frame_nav, user, "3").hide(user).commit();
        fm.beginTransaction().add(R.id.frame_nav, search, "2").hide(search).commit();
        fm.beginTransaction().add(R.id.frame_nav, home, "1").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            fm.beginTransaction().hide(active).show(home).commit();
                            active = home;
                            return true;
                        case R.id.nav_search:
                            fm.beginTransaction().hide(active).show(search).commit();
                            active = search;
                            return true;
                        case R.id.nav_user:
                            fm.beginTransaction().hide(active).show(user).commit();
                            active = user;
                            return true;
                    }
                    return false;
                }
            };

}
