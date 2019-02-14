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

import com.example.dung.assigment_update.Adapter.KhoanChiAdapter;
import com.example.dung.assigment_update.Model.KhoanChi;
import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Khoan_Chi_Fragment extends Fragment {

    public Khoan_Chi_Fragment() {
    }

    private View rootview;
    Database database;
    ListView lv_khoanchi;
    ArrayList<KhoanChi> list;
    KhoanChiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.khoan_chi_fragment,container,false);
        initView();
        return rootview;
    }

    private void initView() {

        lv_khoanchi = rootview.findViewById(R.id.lv_khoanchi);
        database = new Database(getActivity());
        list = new ArrayList<>();
        adapter = new KhoanChiAdapter(getActivity(), R.layout.list_item_khoan_chi,list);

        Cursor datakhoanchi = database.GetData("SELECT * FROM KHOANCHI");
        list.clear();
        while(datakhoanchi.moveToNext()){
            int a = datakhoanchi.getInt(0);
            String b = datakhoanchi.getString(2);
            list.add(new KhoanChi(a,b));
        }
        adapter.notifyDataSetChanged();

        lv_khoanchi.setAdapter(adapter);

    }
}
