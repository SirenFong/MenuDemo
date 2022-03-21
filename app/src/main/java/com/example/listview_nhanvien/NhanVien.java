package com.example.listview_nhanvien;


import java.io.Serializable;
import java.util.Arrays;

public class NhanVien implements Serializable {
    private int maso;
    private String hoten;
    private String gioitinh;
    private String donvi;
    private byte[] img;

    public NhanVien() {
    }

    public NhanVien(int maso, String hoten, String gioitinh, String donvi, byte[] img) {
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.donvi = donvi;
        this.img = img;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getMaso() {
        return maso;
    }

    public void setMaso(int maso) {
        this.maso = maso;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maso=" + maso +
                ", hoten='" + hoten + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", donvi='" + donvi + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
