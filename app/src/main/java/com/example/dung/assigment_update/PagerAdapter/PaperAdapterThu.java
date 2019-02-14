package com.example.dung.assigment_update.PagerAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dung.assigment_update.Fragment_Con.Khoan_Thu_Fragment;
import com.example.dung.assigment_update.Fragment_Con.Loai_Thu_Fragment;

public class PaperAdapterThu extends FragmentStatePagerAdapter {
    public PaperAdapterThu(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment f = null;
        switch (i){
            case 0:
                f = new Khoan_Thu_Fragment();
                break;
            case 1:
                f = new Loai_Thu_Fragment();
                break;
        }

        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String a = "";

        switch (position){
            case 0:
                a = "Khoản Thu";
                break;
            case 1:
                a = "Loại Thu";
                break;
        }
        return a;
    }
}
