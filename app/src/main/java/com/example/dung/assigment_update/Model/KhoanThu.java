package com.example.dung.assigment_update.Model;

public class KhoanThu {

    int id;
    String KhoanThu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKhoanThu() {
        return KhoanThu;
    }

    public void setKhoanThu(String khoanThu) {
        KhoanThu = khoanThu;
    }

    public KhoanThu(int id, String khoanThu) {

        this.id = id;
        KhoanThu = khoanThu;
    }
}
