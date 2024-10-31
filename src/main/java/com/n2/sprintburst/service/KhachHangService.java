/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.service;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.KhachHang;
import com.n2.sprintburst.entity.NhanVien;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class KhachHangService {

    public Session session;

    public void create(KhachHang kh) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {
                s.persist(kh);
                s.flush();
            });
        } catch (Exception e) {
            throw e;
        }

    }

    public List<KhachHang> getAllKhachHang() {
        session = HibernateConfig.getSessionFactory().openSession();
        List<KhachHang> list = session.createQuery("from KhachHang", KhachHang.class).list();
        session.close();//        

        return list;
    }

    public KhachHang getKhachHangById(String id) {
        session = HibernateConfig.getSessionFactory().openSession();
        KhachHang nhanVien = session.get(KhachHang.class, id);
        session.close();
        return nhanVien;
    }

    public void saveKhachHang(KhachHang khachHang) {
        session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(khachHang); // Sử dụng persist cho đối tượng mới
        session.getTransaction().commit();
        session.close();
    }

    public void updateKhachHang(KhachHang khachHang) {
        session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(khachHang); // Sử dụng merge để cập nhật đối tượng đã tồn tại
        session.getTransaction().commit();
        session.close();

    }

    public void deleteKhachHang(String id) {
        session = HibernateConfig.getSessionFactory().openSession();

        try {
            // Bắt đầu transaction
            session.getTransaction().begin();

            // Tìm khách hàng theo id
            KhachHang khachHang = session.get(KhachHang.class, id);
            if (khachHang != null) {
                // Xóa khách hàng
                session.remove(khachHang);
            }

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;  // Ném lại lỗi để xử lý ở tầng giao diện
        } finally {
            session.close();  // Đảm bảo session luôn được đóng sau khi thao tác
        }
    }

    public boolean isEmpty(JTextField txt, String mss) {
        if (txt.getText().equals("")) {
            txt.getCursor();
            txt.requestFocus();
            txt.setBackground(Color.lightGray);
            JOptionPane.showMessageDialog(null, mss);
            return true;
        } else {
            return false;
        }
    }

    public KhachHang timKiemKhachHang(String makh) {
        List<KhachHang> khachHangList = getAllKhachHang();
        for (KhachHang kh : khachHangList) {
            if (kh.getMaKhachHang().equals(makh)) {
                return kh;
            }
        }
        return null;
    }

    public boolean checkSoDienThoaiTrung(String soDienThoai) {
        List<KhachHang> dsKhachHang = getAllKhachHang(); // Lấy danh sách tất cả khách hàng từ cơ sở dữ liệu
        for (KhachHang kh : dsKhachHang) {
            System.out.println("Số điện thoại kiểm tra: " + kh.getDienThoai());
            if (kh.getDienThoai().equals(soDienThoai)) {
                return true; // Số điện thoại đã tồn tại
            }
        }
        return false;
    }
}
