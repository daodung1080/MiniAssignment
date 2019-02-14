package com.example.dung.assigment_update.Model;

public class LoaiChi {

    int id;
    String LoaiChi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiChi() {
        return LoaiChi;
    }

    public void setLoaiChi(String loaiChi) {
        LoaiChi = loaiChi;
    }

    public LoaiChi(int id, String loaiChi) {

        this.id = id;
        LoaiChi = loaiChi;
    }
}
