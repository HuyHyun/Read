package com.example.read.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.read.Adapter.Customadapter;
import com.example.read.Models.MainActivity;
import com.example.read.R;
import com.example.read.Services.TinTuc;
import com.example.read.Services.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Search_Fragment extends Fragment {

    public ArrayList<TinTuc> tinTucs;
    SearchView searchView;
    ListView listView;
    Customadapter adapter;
    SwipeRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = (SearchView) view.findViewById(R.id.search);
        listView = (ListView) view.findViewById(R.id.list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_search);
        tinTucs = new ArrayList<>();

        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thoitranghitech.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/tintuctrongngay.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/bongda.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/asiancup2019.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/anninhhinhsu.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thoitrang.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/bantrecuocsong.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/taichinhbatdongsan.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/amthuc.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/lamdep.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/phim.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/giaoducduhoc.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/canhacmtv.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thethao.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/phithuongkyquac.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/congnghethongtin.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/oto.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thitruongtieudung.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/dulich24h.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/suckhoedoisong.rss");
        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/cuoi24h.rss");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (adapter != null)
                    adapter.filter(newText.trim());
                return false;
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tinTucs.clear();
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thoitranghitech.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/tintuctrongngay.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/bongda.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/asiancup2019.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/anninhhinhsu.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thoitrang.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/bantrecuocsong.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/taichinhbatdongsan.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/amthuc.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/lamdep.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/phim.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/giaoducduhoc.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/canhacmtv.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thethao.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/phithuongkyquac.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/congnghethongtin.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/oto.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/thitruongtieudung.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/dulich24h.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/suckhoedoisong.rss");
                        new SearchAll().execute("https://cdn.24h.com.vn/upload/rss/cuoi24h.rss");
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        refreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), noiDungActivity.class);
                intent.putExtra("dulieu", tinTucs.get(position));
                MainActivity.database.QueryData("DELETE FROM lichsu WHERE link = '" + tinTucs.get(position).getLink() + "'");
                MainActivity.database.INSERT_Data("lichsu", tinTucs.get(position));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.amin_enter, R.anim.anim_exit);

            }
        });
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    public class SearchAll extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {

                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }


        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListdescription = document.getElementsByTagName("description");
            String hinhAnh = "";
            String tieuDe = "";
            String link = "";
            String date = "";

            for (int i = 0; i < nodeList.getLength(); i++) {
                String cData = nodeListdescription.item(i + 1).getTextContent();
                Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = pattern.matcher(cData);
                if (matcher.find()) {
                    hinhAnh = matcher.group(1);

                }

                Element element = (Element) nodeList.item(i);
                tieuDe = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                date = parser.getValue(element, "pubDate");
                tinTucs.add(new TinTuc(tieuDe, hinhAnh, link, date));


            }

            Log.d("tonghinh", tinTucs.size() + "");
            adapter = new Customadapter(getActivity(), R.layout.dong_layout_listview, tinTucs);
            listView.setAdapter(adapter);


        }
    }


}
