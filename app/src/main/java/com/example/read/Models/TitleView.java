package com.example.read.Models;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.read.Adapter.Customadapter;
import com.example.read.R;
import com.example.read.Services.RSSService;
import com.example.read.Views.noiDungActivity;

public class TitleView extends Fragment {
    ListView listView;
    String title;
    SwipeRefreshLayout swipeRefreshLayout;


    public TitleView(String title) {
        this.title = title;
    }

    public TitleView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        listView = (ListView) view.findViewById(R.id.listview1);
        LoadData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadData();
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 3000); // Delay in millis
            }
        });
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );
        return view;
    }

    public void LoadData() {
        final RSSService Rss = new RSSService();
        Rss.execute(title);
        Rss.adapter = new Customadapter(getActivity(), R.layout.dong_layout_listview, Rss.listTinTuc);
        listView.setAdapter(Rss.adapter);
        swipeRefreshLayout.setRefreshing(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.database.QueryData("DELETE FROM lichsu WHERE link = '" + Rss.listTinTuc.get(position).getLink() + "'");
                MainActivity.database.INSERT_Data("lichsu", Rss.listTinTuc.get(position));
                Intent intent = new Intent(getActivity(), noiDungActivity.class);
                intent.putExtra("dulieu", Rss.listTinTuc.get(position));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.amin_enter, R.anim.anim_exit);
            }
        });
    }
}
