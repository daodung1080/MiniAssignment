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
import com.example.dung.assigment_update.Model.KhoanChi;
import com.example.dung.assigment_update.R;
import com.example.dung.assigment_update.SQlite.Database;

import java.util.List;

public class KhoanChiAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<KhoanChi> khoanChiList;

    public KhoanChiAdapter(Context context, int layout, List<KhoanChi> khoanChiList) {
        this.context = context;
        this.layout = layout;
        this.khoanChiList = khoanChiList;
    }

    @Override
    public int getCount() {
        return khoanChiList.size();
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
        TextView txt_khoanchi;
        ImageView img_edt_khoanchi,img_dlt_khoanchi;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txt_khoanchi = view.findViewById(R.id.txt_khoanchi);
            holder.img_edt_khoanchi = view.findViewById(R.id.img_edt_khoanchi);
            holder.img_dlt_khoanchi = view.findViewById(R.id.img_dlt_khoanchi);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        final KhoanChi kc = khoanChiList.get(i);
        holder.txt_khoanchi.setText(kc.getKhoanChi());

        final Database database = new Database(context);

        holder.img_edt_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setTitle("Cập nhật thông tin");

                LayoutInflater inflaterdialog = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewdialog = inflaterdialog.inflate(R.layout.dialog_sua,null);
                a.setView(viewdialog);
                final EditText edt_sua = viewdialog.findViewById(R.id.edt_sua);

                edt_sua.setText(kc.getKhoanChi());

                a.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                a.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String a = edt_sua.getText().toString();
                        int b = kc.getId();
                        if(a.isEmpty()){
                            Toast.makeText(context, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            database.SendData("UPDATE KHOANCHI SET KHOANCHI ='"+a+"' WHERE ID="+b+" ");
                            ((MainActivity)context).recreate();
                            Toast.makeText(context, "Cập nhật khoản chi thành công", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                a.show();
            }
        });
        holder.img_dlt_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setTitle("Xóa thông tin chi");
                a.setMessage("Bạn có đồng ý xóa thông tin chi này?");

                a.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                a.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int b = kc.getId();
                        database.SendData("DELETE FROM KHOANCHI WHERE ID="+b+" ");
                        ((MainActivity)context).recreate();
                        Toast.makeText(context, "Xóa thông tin chi thành công", Toast.LENGTH_SHORT).show();
                    }
                });

                a.show();
            }
        });


        return view;
    }
}
