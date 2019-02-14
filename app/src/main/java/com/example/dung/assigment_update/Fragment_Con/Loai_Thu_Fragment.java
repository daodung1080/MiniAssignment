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

        import com.example.dung.assigment_update.Adapter.LoaiThuAdapter;
        import com.example.dung.assigment_update.Model.LoaiThu;
        import com.example.dung.assigment_update.R;
        import com.example.dung.assigment_update.SQlite.Database;

        import java.util.ArrayList;

public class Loai_Thu_Fragment extends Fragment {

    public Loai_Thu_Fragment() {
    }

    private View rootview;
    ListView lv_loaithu;
    LoaiThuAdapter adapter;
    ArrayList<LoaiThu> list;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.loai_thu_fragment,container,false);
        initView();
        return rootview;
    }

    private void initView() {

        lv_loaithu = rootview.findViewById(R.id.lv_loaithu);
        list = new ArrayList<>();
        database = new Database(getActivity());
        adapter = new LoaiThuAdapter(getActivity(), R.layout.list_item_loai_thu,list);

        Cursor dataloaithu = database.GetData("SELECT * FROM LOAITHU");
        list.clear();
        while (dataloaithu.moveToNext()){
            int a = dataloaithu.getInt(0);
            String b = dataloaithu.getString(2);
            list.add(new LoaiThu(a,b));
        }
        adapter.notifyDataSetChanged();

        lv_loaithu.setAdapter(adapter);

    }
}
