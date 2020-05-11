package com.example.read.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.read.Adapter.ViewPagerAdapter;
import com.example.read.Models.TitleView;
import com.example.read.R;
import com.google.android.material.tabs.TabLayout;

public class Home_Fragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewPager viewPager;
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);

        addTabs(viewPager);

        ((TabLayout) view.findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
        return view;
    }
    public void addTabs(ViewPager viewPager) {
        assert getFragmentManager() != null;
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/tintuctrongngay.rss"), "Tin tức trong ngày");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/bongda.rss"), "Bóng đá");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/thoitranghitech.rss"), "Hi-tech");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/congnghethongtin.rss"), "Công nghệ thông tin");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/anninhhinhsu.rss"), "An ninh");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/thoitrang.rss"), "Thời trang");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/taichinhbatdongsan.rss"), "Tài chính");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/amthuc.rss"), "Ẩm thực");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/lamdep.rss"), "Làm đẹp");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/phim.rss"), "Phim");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/giaoducduhoc.rss"), "Giáo dục");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/bantrecuocsong.rss"), "Cuộc sống");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/canhacmtv.rss"), "Ca nhạc");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/thethao.rss"), "Thể thao");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/phithuongkyquac.rss"), "Phi thường");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/oto.rss"), "Ô tô");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/thitruongtieudung.rss"), "Thị trường");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/dulich24h.rss"), "Du lịch");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/suckhoedoisong.rss"), "Sức khỏe");
        adapter.add(new TitleView("https://cdn.24h.com.vn/upload/rss/cuoi24h.rss"), "Cười");
        viewPager.setAdapter(adapter);
    }
}
