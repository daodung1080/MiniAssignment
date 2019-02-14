package com.example.dung.assigment_update.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dung.assigment_update.Fragment.Chi_Fragment;
import com.example.dung.assigment_update.Fragment.ThongKe_Fragment;
import com.example.dung.assigment_update.Fragment.Thu_Fragment;

public class PagerAdapterMain extends FragmentStatePagerAdapter {
    public PagerAdapterMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment f = null;
        switch(i){
            case 0:
                f = new Thu_Fragment();
                break;
            case 1:
                f = new Chi_Fragment();
                break;
            case 2:
                f = new ThongKe_Fragment();
                break;
        }

        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
