/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.service;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.NhanVien;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class NhanVienService {

    public Session session;

    public List<NhanVien> getAllNhanVien() {
        session = HibernateConfig.getSessionFactory().openSession();
        List<NhanVien> list = session.createQuery("from NhanVien", NhanVien.class).list();
        session.close();
        return list;
    }

    public NhanVien getNhanVienById(String id) {
        session = HibernateConfig.getSessionFactory().openSession();
        NhanVien nhanVien = session.get(NhanVien.class, id);
        session.close();
        return nhanVien;
    }

    public void saveNhanVien(NhanVien nhanVien) {
        session = HibernateConfig.getSessionFactory().openSession();
        session.getTransaction().begin();
        // Sử dụng merge thay vì persist để cập nhật nếu đối tượng đã tồn tại
        session.persist(nhanVien);
        session.getTransaction().commit();
        session.close();
    }

    public void updateNhanVien(NhanVien nhanVien) {
        session = HibernateConfig.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.merge(nhanVien); // Sử dụng merge để cập nhật đối tượng đã tồn tại
        session.getTransaction().commit();
        session.close();
    }

    public void deleteNhanVien(String id) {
        session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        NhanVien nhanVien = getNhanVienById(id);
        if (nhanVien != null) {
            session.remove(nhanVien);
        }
        session.getTransaction().commit();
        session.close();
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

    public NhanVien timKiemNhanVien(String manv) {
        List<NhanVien> NhanVienList = getAllNhanVien();
        for (NhanVien nv : NhanVienList) {
            if (nv.getHoTen().equals(manv)) {
                return nv;
            }
        }
        return null;
    }
    
}
