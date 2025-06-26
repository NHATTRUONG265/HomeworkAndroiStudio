package com.example.kt_nhom1;

public class SanPham {
    private String ten;
    private String gia;
    private int imageRes;
    private String mota;

    public SanPham(String ten, String gia, int imageRes, String mota) {
        this.ten = ten;
        this.gia = gia;
        this.imageRes = imageRes;
        this.mota = mota;
    }

    public String getTen() {
        return ten;
    }

    public String getGia() {
        return gia;
    }

    public int getImageRes() {
        return imageRes;
    }
    public String getMota() {
        return mota;
    }

}

