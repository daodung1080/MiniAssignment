package com.example.dung.assigment_update.Model;

public class KhoanChi {

    int id;
    String KhoanChi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKhoanChi() {
        return KhoanChi;
    }

    public void setKhoanChi(String khoanChi) {
        KhoanChi = khoanChi;
    }

    public KhoanChi(int id, String khoanChi) {

        this.id = id;
        KhoanChi = khoanChi;
    }
}
