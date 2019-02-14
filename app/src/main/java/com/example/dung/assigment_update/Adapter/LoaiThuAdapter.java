package com.example.dung.assigment_update.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dung.assigment_update.MainActivity;
import com.example.dung.assigment_update.Model.LoaiThu;
import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.util.List;

public class LoaiThuAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<LoaiThu> loaiThuList;

    public LoaiThuAdapter(Context context, int layout, List<LoaiThu> loaiThuList) {
        this.context = context;
        this.layout = layout;
        this.loaiThuList = loaiThuList;
    }

    @Override
    public int getCount() {
        return loaiThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView txt_loaithu;
        ImageView img_edt_loaithu,img_dlt_loaithu;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txt_loaithu = view.findViewById(R.id.txt_loaithu);
            holder.img_edt_loaithu = view.findViewById(R.id.img_edt_loaithu);
            holder.img_dlt_loaithu = view.findViewById(R.id.img_dlt_loaithu);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        final LoaiThu lt = loaiThuList.get(i);
        holder.txt_loaithu.setText(lt.getLoaiThu());
        final Database database = new Database(context);

        holder.img_edt_loaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setTitle("Cập nhật thông tin");

                LayoutInflater inflaterdialog = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewdialog = inflaterdialog.inflate(R.layout.dialog_sua,null);
                a.setView(viewdialog);
                final EditText edt_sua = viewdialog.findViewById(R.id.edt_sua);

                edt_sua.setText(lt.getLoaiThu());

                a.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                a.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String a = edt_sua.getText().toString();
                        int b = lt.getId();
                        if(a.isEmpty()){
                            Toast.makeText(context, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            database.SendData("UPDATE LOAITHU SET LOAITHU ='"+a+"' WHERE ID="+b+" ");
                            ((MainActivity)context).recreate();
                            Toast.makeText(context, "Cập nhật loại thu thành công", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                a.show();
            }
        });
        holder.img_dlt_loaithu.setOnClickListener(new View.OnClickListener() {
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
                        int b = lt.getId();
                        database.SendData("DELETE FROM LOAITHU WHERE ID="+b+" ");
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
