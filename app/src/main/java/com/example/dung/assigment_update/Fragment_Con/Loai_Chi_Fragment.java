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

import com.example.dung.assigment_update.Adapter.LoaiChiAdapter;
import com.example.dung.assigment_update.Model.LoaiChi;
import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Loai_Chi_Fragment extends Fragment {

    public Loai_Chi_Fragment() {
    }

    private View rootview;
    Database database;
    ListView lv_loaichi;
    LoaiChiAdapter adapter;
    ArrayList<LoaiChi> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.loai_chi_fragment,container,false);
        initView();
        return rootview;
    }

    private void initView() {

        lv_loaichi = rootview.findViewById(R.id.lv_loaichi);
        list = new ArrayList<>();
        database = new Database(getActivity());
        adapter = new LoaiChiAdapter(getActivity(), R.layout.list_item_loai_chi,list);

        Cursor dataloaichi = database.GetData("SELECT * FROM LOAICHI");
        list.clear();
        while (dataloaichi.moveToNext()){
            int a = dataloaichi.getInt(0);
            String b = dataloaichi.getString(2);
            list.add(new LoaiChi(a,b));
        }
        adapter.notifyDataSetChanged();

        lv_loaichi.setAdapter(adapter);

    }
}
