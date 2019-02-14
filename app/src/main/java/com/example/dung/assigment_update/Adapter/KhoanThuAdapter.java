package com.example.dung.assigment_update.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dung.assigment_update.MainActivity;
import com.example.dung.assigment_update.Model.KhoanThu;
import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class KhoanThuAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<KhoanThu> khoanThuList;

    public KhoanThuAdapter(Context context, int layout, List<KhoanThu> khoanThuList) {
        this.context = context;
        this.layout = layout;
        this.khoanThuList = khoanThuList;
    }

    @Override
    public int getCount() {
        return khoanThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txt_khoanthu;
        ImageView img_edt_khoanthu,img_dlt_khoanthu;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txt_khoanthu = view.findViewById(R.id.txt_khoanthu);
            holder.img_edt_khoanthu = view.findViewById(R.id.img_edt_khoanthu);
            holder.img_dlt_khoanthu = view.findViewById(R.id.img_dlt_khoanthu);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        final KhoanThu kt = khoanThuList.get(i);
        holder.txt_khoanthu.setText(kt.getKhoanThu());

        final Database database = new Database(context);

        holder.img_edt_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setTitle("Cập nhật thông tin");

                LayoutInflater inflaterdialog = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewdialog = inflaterdialog.inflate(R.layout.dialog_sua,null);
                a.setView(viewdialog);
                final EditText edt_sua = viewdialog.findViewById(R.id.edt_sua);

                edt_sua.setText(kt.getKhoanThu());

                a.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                a.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String a = edt_sua.getText().toString();
                        int b = kt.getId();
                        if(a.isEmpty()){
                            Toast.makeText(context, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            database.SendData("UPDATE KHOANTHU SET KHOANTHU ='"+a+"' WHERE ID="+b+" ");
                            ((MainActivity)context).recreate();
                            Toast.makeText(context, "Cập nhật khoản thu thành công", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                a.show();
            }
        });
        holder.img_dlt_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setTitle("Xóa thông tin thu");
                a.setMessage("Bạn có đồng ý xóa thông tin thu này?");

                a.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                a.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int b = kt.getId();
                        database.SendData("DELETE FROM KHOANTHU WHERE ID="+b+" ");
                        ((MainActivity)context).recreate();
                        Toast.makeText(context, "Xóa thông tin thu thành công", Toast.LENGTH_SHORT).show();
                    }
                });

                a.show();
            }
        });

        return view;
    }
}
