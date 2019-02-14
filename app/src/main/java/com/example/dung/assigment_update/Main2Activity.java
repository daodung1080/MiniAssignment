package com.example.dung.assigment_update;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final ProgressDialog p = new ProgressDialog(Main2Activity.this);
        p.setMessage("Đang khởi tạo chương trình");
        p.setTitle("Assigment Đào Dũng");
        p.show();
        final Intent i = new Intent(Main2Activity.this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                p.dismiss();
                startActivity(i);
                finish();
            }
        };
        Handler h = new Handler();
        h.postDelayed(r,3000);
    }
}
