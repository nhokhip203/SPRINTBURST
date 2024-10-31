/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.DTO;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class NhanVienDTO {
    public NhanVien getUserByCredentials(String dienThoai, String password) {
        Transaction transaction = null;
        NhanVien nv = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // Bắt đầu transaction
            transaction = session.beginTransaction();

            // Tạo câu truy vấn để tìm user dựa trên username và password
            String hql = "FROM NhanVien U WHERE U.dienThoai = :dienThoai AND U.password = :password";
            Query<NhanVien> query = session.createQuery(hql, NhanVien.class);
            query.setParameter("dienThoai", dienThoai);
            query.setParameter("password", password);

            // Lấy kết quả
            List<NhanVien> nvs = query.getResultList();

            // Nếu tồn tại user thỏa mãn điều kiện
            if (!nvs.isEmpty()) {
                nv = nvs.get(0);
            }

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return nv;
    }
}
