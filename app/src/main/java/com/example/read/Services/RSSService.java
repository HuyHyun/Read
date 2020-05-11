package com.example.read.Services;

import android.os.AsyncTask;

import com.example.read.Adapter.Customadapter;

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


public class RSSService extends AsyncTask<String, Void, String> {

    public ArrayList<TinTuc> listTinTuc = new ArrayList<>();
    public Customadapter adapter;

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

        listTinTuc.clear();
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
            listTinTuc.add(new TinTuc(tieuDe, hinhAnh, link, date));
        }
        adapter.notifyDataSetChanged();
        adapter.refresh(listTinTuc);

    }
}

