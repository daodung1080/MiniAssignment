package com.example.dung.assigment_update.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dung.assigment_update.PagerAdapter.PaperAdapterThu;
import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Thu_Fragment extends Fragment {

    public Thu_Fragment() {
    }

    private View rootview;
    ViewPager pv_thu;
    PaperAdapterThu adapter;
    TabLayout tl_thu;
    FloatingActionButton fab_thu;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.thu_fragment,container,false);
        initView();
        return rootview;
    }

    private void initView() {
        tl_thu = rootview.findViewById(R.id.tl_thu);
        pv_thu = rootview.findViewById(R.id.pv_thu);
        adapter = new PaperAdapterThu(getActivity().getSupportFragmentManager());
        pv_thu.setAdapter(adapter);
        tl_thu.setupWithViewPager(pv_thu);

        database = new Database(getContext());

        fab_thu = rootview.findViewById(R.id.fab_thu);
        fab_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pv_thu.getCurrentItem() == 0){
                    AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                    a.setTitle("Thêm thông tin khoản thu");

                    View view1 = getLayoutInflater().inflate(R.layout.dialog_them_tatca,null);

                    a.setView(view1);

                    final EditText edt = view1.findViewById(R.id.edt_tatca);
                    a.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final String b = edt.getText().toString();
                            if(b.isEmpty()){
                                Toast.makeText(getActivity(), "Vui lòng không để trống khoản thu", Toast.LENGTH_SHORT).show();
                            }
                            else{

                                final ProgressDialog p1 = new ProgressDialog(getActivity());
                                p1.setMessage("Đang thêm khoản thu vào database");
                                p1.getWindow().setGravity(Gravity.BOTTOM);
                                p1.show();

                                Runnable r1 = new Runnable() {
                                    @Override
                                    public void run() {
                                        String c = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                                        database.SendData("INSERT INTO KHOANTHU VALUES(NULL, '"+c+"' , '"+b+"' )");
                                        Toast.makeText(getContext(), "Thêm khoản thu vào database thành công", Toast.LENGTH_SHORT).show();
                                        p1.dismiss();
                                        adapter = new PaperAdapterThu(getActivity().getSupportFragmentManager());
                                        pv_thu.setAdapter(adapter);
                                        database.SendData("INSERT INTO NGAYTHANG VALUES('"+c+"')");
                                    }
                                };
                                Handler h = new Handler();
                                h.postDelayed(r1,2000);
                            }

                        }
                    });
                    a.show();
                }
                if(pv_thu.getCurrentItem() == 1){
                    AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                    a.setTitle("Thêm thông tin loại thu");

                    View view1 = getLayoutInflater().inflate(R.layout.dialog_them_tatca,null);

                    a.setView(view1);

                    final EditText edt1 = view1.findViewById(R.id.edt_tatca);
                    a.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final String d = edt1.getText().toString();
                            if(d.isEmpty()){
                                Toast.makeText(getActivity(), "Vui lòng không để trống loại thu", Toast.LENGTH_SHORT).show();
                            }
                            else{

                                final ProgressDialog p2 = new ProgressDialog(getActivity());
                                p2.setMessage("Đang thêm loại thu vào database");
                                p2.getWindow().setGravity(Gravity.BOTTOM);
                                p2.show();

                                Runnable r1 = new Runnable() {
                                    @Override
                                    public void run() {
                                        String c = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                                        database.SendData("INSERT INTO LOAITHU VALUES(NULL, '"+c+"' , '"+d+"' )");
                                        Toast.makeText(getContext(), "Thêm loại thu vào database thành công", Toast.LENGTH_SHORT).show();
                                        p2.dismiss();
                                        adapter = new PaperAdapterThu(getActivity().getSupportFragmentManager());
                                        pv_thu.setAdapter(adapter);
                                        pv_thu.setCurrentItem(1);
                                        database.SendData("INSERT INTO NGAYTHANG VALUES('"+c+"')");
                                    }
                                };
                                Handler h = new Handler();
                                h.postDelayed(r1,2000);
                            }

                        }
                    });
                    a.show();
                }
            }
        });
    }
}
