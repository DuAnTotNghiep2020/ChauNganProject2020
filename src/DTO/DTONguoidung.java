/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DTONguoidung {
    int IdNguoiDung;
    String TenNguoiDung;
    String GioiTinh;
    String NgaySinh;
    String NgayVaoLam;
    int CMND;
    String TenDangNhap;
    String MatKhau;
    int Quyen;

    public DTONguoidung() {
    }

    public DTONguoidung(int IdNguoiDung, String TenNguoiDung, String GioiTinh, String NgaySinh, String NgayVaoLam, int CMND, String TenDangNhap, String MatKhau, int Quyen) {
        this.IdNguoiDung = IdNguoiDung;
        this.TenNguoiDung = TenNguoiDung;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.NgayVaoLam = NgayVaoLam;
        this.CMND = CMND;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Quyen = Quyen;
    }

    public int getIdNguoiDung() {
        return IdNguoiDung;
    }

    public void setIdNguoiDung(int IdNguoiDung) {
        this.IdNguoiDung = IdNguoiDung;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String TenNguoiDung) {
        this.TenNguoiDung = TenNguoiDung;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(String NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public int getCMND() {
        return CMND;
    }

    public void setCMND(int CMND) {
        this.CMND = CMND;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getQuyen() {
        return Quyen;
    }

    public void setQuyen(int Quyen) {
        this.Quyen = Quyen;
    }

   
}
