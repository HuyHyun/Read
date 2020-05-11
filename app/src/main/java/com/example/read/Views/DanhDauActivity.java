package com.example.read.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.read.Adapter.Customadapter;
import com.example.read.Models.MainActivity;
import com.example.read.R;
import com.example.read.Services.TinTuc;

import java.util.ArrayList;

import static com.example.read.Models.MainActivity.database;

public class DanhDauActivity extends AppCompatActivity {
    ListView listView;
    ImageButton btnBack;
    static ArrayList<TinTuc> arrayList = new ArrayList<>();
    ;
    public static Customadapter adapter;
    ImageView btnDeleteAll;
    static ImageView imgEmply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_dau);
        btnDeleteAll = (ImageView) findViewById(R.id.btnDeleteAll);
        listView = (ListView) findViewById(R.id.listview1);
        imgEmply = (ImageView) findViewById(R.id.emptyfolder);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        adapter = new Customadapter(this, R.layout.dong_layout_listview, arrayList);
        listView.setAdapter(adapter);
        GetDataDanhDau();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog1XoaCv(arrayList.get(position).getId());
                GetDataDanhDau();

                return false;
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.database.INSERT_Data("lichsu", arrayList.get(position));
                Intent intent = new Intent(DanhDauActivity.this, noiDungActivity.class);
                intent.putExtra("dulieu", arrayList.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.amin_enter, R.anim.anim_exit);

            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogXoaCv();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }

    public void DialogXoaCv() {
        AlertDialog.Builder diaglogXoa = new AlertDialog.Builder(this);
        diaglogXoa.setMessage("Bạn có muốn xóa tất cả bài viết?");
        diaglogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM danhdau");
                GetDataDanhDau();
            }
        });
        diaglogXoa.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        diaglogXoa.show();
    }


    public void Dialog1XoaCv(final int ids) {
        AlertDialog.Builder diaglogXoa = new AlertDialog.Builder(this);
        diaglogXoa.setMessage("Bạn có muốn bài viết này không?");
        diaglogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM danhdau WHERE id = '" + ids + "'");
                Toast.makeText(DanhDauActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                GetDataDanhDau();
            }
        });
        diaglogXoa.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        diaglogXoa.show();
    }

    public static void GetDataDanhDau() {

        Cursor cursor = database.GetData("SELECT * FROM danhdau ORDER BY id DESC");
        if (arrayList != null) {
            arrayList.clear();
        }
        while (cursor.moveToNext()) {
            arrayList.add(new TinTuc(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
        adapter.notifyDataSetChanged();
        if (arrayList.size() == 0) {
            imgEmply.setVisibility(View.VISIBLE);
        } else
            imgEmply.setVisibility(View.INVISIBLE);
    }
}
