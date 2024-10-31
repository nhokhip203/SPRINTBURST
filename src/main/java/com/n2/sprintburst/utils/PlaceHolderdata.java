/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.utils;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.KhachHang;
import com.n2.sprintburst.entity.MauSac;
import com.n2.sprintburst.entity.NhanVien;
import com.n2.sprintburst.entity.SanPham;
import com.n2.sprintburst.entity.SanPhamChiTiet;
import com.n2.sprintburst.service.NhanVienService;
import java.time.LocalDateTime;

/**
 *
 * @author Mtt
 */
public class PlaceHolderdata {

    static NhanVien nv1 = new NhanVien(0, "NV1", "email1@gmai.com ", "09127366329", "password", "Quốc Minh", null, null, false, LocalDateTime.now(), LocalDateTime.now(), null, true);
    static NhanVien nv2 = new NhanVien(0, "NV2", "email2@gmai.com ", "09121033447", "password", "Thu Hằng", null, null, false, LocalDateTime.now(), LocalDateTime.now(), null, true);
    static NhanVien nv3 = new NhanVien(0, "NV3", "email3@gmai.com ", "09122958434", "password", "Minh Châu", null, null, false, LocalDateTime.now(), LocalDateTime.now(), null, true);
    static NhanVien nv4 = new NhanVien(0, "NV4", "email4@gmai.com ", "09125761680", "password", "Hữu Quý", null, null, false, LocalDateTime.now(), LocalDateTime.now(), null, true);
    static NhanVien nv5 = new NhanVien(0, "NV5", "email5@gmai.com ", "09122757636", "password", "A A", null, null, true, LocalDateTime.now(), LocalDateTime.now(), null, true);

    static KhachHang kh1 = new KhachHang(0, "KH1", "Khách lẻ", null, "09129579059", null, null, LocalDateTime.now(), nv1);
    static KhachHang kh2 = new KhachHang(0, "KH2", "Minh", null, "09123444631", null, null, LocalDateTime.now(), nv2);
    static KhachHang kh3 = new KhachHang(0, "KH3", "Thu", "thu@gmail.com", "09129217951", null, null, LocalDateTime.now(), nv2);
    static KhachHang kh4 = new KhachHang(0, "KH4", "Hiền", null, "9122862274", null, null, LocalDateTime.now(), nv3);
    static KhachHang kh5 = new KhachHang(0, "KH5", "Quân", null, "09127665736", null, null, LocalDateTime.now(), nv4);

    public static void insertNhanViens() {
        HibernateConfig.getSessionFactory().inTransaction(s -> {

            s.persist(nv1);
            s.persist(nv2);
            s.persist(nv3);
            s.persist(nv4);
            s.persist(nv5);
            s.flush();
        });
    }

    public static void insertKhachHangs() {
        HibernateConfig.getSessionFactory().inTransaction(s -> {
            s.persist(kh1);
            s.persist(kh2);
            s.persist(kh3);
            s.persist(kh4);
            s.persist(kh5);
            s.flush();
        });
    }

    public static void insertData() {

        insertNhanViens();
        insertKhachHangs();
    }

    public static void main(String[] args) {
        insertData();
    }
}
