package com.example.dung.assigment_update.Fragment_Con;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dung.assigment_update.Adapter.KhoanThuAdapter;
import com.example.dung.assigment_update.Model.KhoanThu;
import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.util.ArrayList;

public class Khoan_Thu_Fragment extends Fragment {

    public Khoan_Thu_Fragment() {
    }

    private View rootview;
    ArrayList<KhoanThu> list;
    KhoanThuAdapter adapter;
    ListView lv_khoanthu;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.khoan_thu_fragment,container,false);

        initView();

        return rootview;
    }

    private void initView() {

        list = new ArrayList<>();
        adapter = new KhoanThuAdapter(getActivity(), R.layout.list_item_khoan_thu,list);
        lv_khoanthu = rootview.findViewById(R.id.lv_khoanthu);
        database = new Database(getActivity());

        Cursor datakt = database.GetData("SELECT * FROM KHOANTHU");
        list.clear();
        while (datakt.moveToNext()){
            int a = datakt.getInt(0);
            String b = datakt.getString(2);
            list.add(new KhoanThu(a,b));
        }

        lv_khoanthu.setAdapter(adapter);
    }
}
