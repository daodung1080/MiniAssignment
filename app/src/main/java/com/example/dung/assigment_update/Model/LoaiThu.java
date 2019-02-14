package com.example.dung.assigment_update.Model;

public class LoaiThu {

    int id;
    String LoaiThu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiThu() {
        return LoaiThu;
    }

    public void setLoaiThu(String loaiThu) {
        LoaiThu = loaiThu;
    }

    public LoaiThu(int id, String loaiThu) {

        this.id = id;
        LoaiThu = loaiThu;
    }
}
