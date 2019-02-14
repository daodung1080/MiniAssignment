package com.example.dung.assigment_update.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ThongKe_Fragment extends Fragment {

    public ThongKe_Fragment() {
    }

    private View rootview;
    List<String> spinnerlist,list1,list2,list3,list4;
    ArrayAdapter<String> spinneradapter,adapter1,adapter2,adapter3,adapter4;
    Spinner spinner;
    Database database;
    int vitri;
    ListView lv_thongke1,lv_thongke2,lv_thongke3,lv_thongke4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.thongke_fragment,container,false);
        initView();
        return rootview;
    }

    private void initView() {
        database = new Database(getContext());
        spinnerlist = new ArrayList<>();
        spinneradapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerlist);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner = rootview.findViewById(R.id.spinner);

        Cursor datathongke = database.GetData("SELECT * FROM NGAYTHANG");
        spinnerlist.clear();
        while (datathongke.moveToNext()) {
            String a = datathongke.getString(0);
            spinnerlist.add(a);
        }

        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(spinnerlist);
        spinnerlist.clear();
        spinnerlist.addAll(hashSet);

        spinneradapter.notifyDataSetChanged();

        spinner.setAdapter(spinneradapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vitri = position;
                final String macdinh = spinnerlist.get(vitri).toString();
                lv_thongke1 = rootview.findViewById(R.id.lv_thongke1);
                list1 = new ArrayList<>();
                adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list1);
                Cursor data1 = database.GetData("SELECT * FROM KHOANTHU WHERE NGAYTHANG = '"+macdinh+"' ");
                list1.clear();
                while (data1.moveToNext()) {
                    String a = data1.getString(2);
                    list1.add(a);
                }
                adapter1.notifyDataSetChanged();
                lv_thongke1.setAdapter(adapter1);

                lv_thongke2 = rootview.findViewById(R.id.lv_thongke2);
                list2 = new ArrayList<>();
                adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list2);
                Cursor data2 = database.GetData("SELECT * FROM LOAITHU WHERE NGAYTHANG = '"+macdinh+"' ");
                list2.clear();
                while (data2.moveToNext()) {
                    String a = data2.getString(2);
                    list2.add(a);
                }
                adapter2.notifyDataSetChanged();
                lv_thongke2.setAdapter(adapter2);

                lv_thongke3 = rootview.findViewById(R.id.lv_thongke3);
                list3 = new ArrayList<>();
                adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list3);
                Cursor data3 = database.GetData("SELECT * FROM KHOANCHI WHERE NGAYTHANG = '"+macdinh+"' ");
                list3.clear();
                while (data3.moveToNext()) {
                    String a = data3.getString(2);
                    list3.add(a);
                }
                adapter3.notifyDataSetChanged();
                lv_thongke3.setAdapter(adapter3);

                lv_thongke4 = rootview.findViewById(R.id.lv_thongke4);
                list4 = new ArrayList<>();
                adapter4 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list4);
                Cursor data4 = database.GetData("SELECT * FROM LOAICHI WHERE NGAYTHANG = '"+macdinh+"' ");
                list4.clear();
                while (data4.moveToNext()) {
                    String a = data4.getString(2);
                    list4.add(a);
                }
                adapter4.notifyDataSetChanged();
                lv_thongke4.setAdapter(adapter4);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
