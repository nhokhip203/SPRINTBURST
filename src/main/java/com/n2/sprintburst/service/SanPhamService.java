/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.service;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.SanPham;
import com.n2.sprintburst.entity.SanPhamChiTiet;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/**
 *
 * @author Mtt
 */
public class SanPhamService {

    public static List<SanPham> getAllActive() {
        try {
            List<SanPham> list;
            EntityManager s = HibernateConfig.getSessionFactory().createEntityManager();

            list = s.createQuery("from SanPham where trangThai = :status", SanPham.class)
                    .setParameter("status", true)
                    .getResultList();
            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<SanPham> getByFilter(boolean status, String keyword) {
        try {
            List<SanPham> list;
            EntityManager s = HibernateConfig.getSessionFactory().createEntityManager();

            StringBuilder query = new StringBuilder("from SanPham where ");

            if (keyword != null) {
                query.append("(maSanPham like :keyword or tenSanPham like :keyword) ");
                query.append("and trangThai = :status");
            } else {
                query.append("trangThai = :status");
            }

            System.err.println(query.toString());
            list = s.createQuery(query.toString(),
                    SanPham.class)
                    .setParameter("status", status)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();

            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void add(SanPham sp) {
        try {

            HibernateConfig.getSessionFactory().inTransaction(s -> {
                List<SanPham> duplicateName = s.createSelectionQuery("from SanPham where tenSanPham like :tenSanPham", SanPham.class).setParameter("tenSanPham", sp.getTenSanPham()).getResultList();

                if (!duplicateName.isEmpty()) {
                    s.createMutationQuery("update SanPham set trangThai = true where tenSanPham like :tenSanPham").setParameter("tenSanPham", sp.getTenSanPham()).executeUpdate();

                    return;

                }

                SanPham found = s.createSelectionQuery("from SanPham order by id desc", SanPham.class).setMaxResults(1).getSingleResultOrNull();
                int newId;
                if (found == null) {
                    newId = 1;
                } else {
                    newId = found.getId() + 1;
                }

                sp.setMaSanPham("SP" + newId);
                sp.setNgayTao(LocalDateTime.now());
                sp.setNgayCapNhat(LocalDateTime.now());
                sp.setTrangThai(true);
                s.persist(sp);
            });

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public static void update(SanPham sp) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {
                SanPham found = s.createQuery("from SanPham where id = :id", SanPham.class)
                        .setParameter("id", sp.getId())
                        .getSingleResult();
                if (found != null) {
                    found.setTenSanPham(sp.getTenSanPham());
                    found.setNgayCapNhat(LocalDateTime.now());
                    s.merge(found);
                    s.flush();
                }
            });

        } catch (Exception e) {
            throw e;
        }

    }

    public static void delete(SanPham sp) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {
                SanPham found = s.createQuery("from SanPham where id = :id", SanPham.class)
                        .setParameter("id", sp.getId())
                        .getSingleResult();
                if (found != null) {
                    found.setNgayXoa(LocalDateTime.now());
                    found.setTrangThai(false);

                    s.merge(found);
                    s.flush();
                }

                List<SanPhamChiTiet> cascade = s.createQuery("from SanPhamChiTiet where sanPham.id = :idSanPham", SanPhamChiTiet.class)
                        .setParameter("idSanPham", sp.getId())
                        .getResultList();

                for (SanPhamChiTiet item : cascade) {
                    item.setNgayXoa(found.getNgayXoa());
                    item.setTrangThai(false);
                    s.merge(item);
                }

                s.flush();

            });

        } catch (Exception e) {
            throw e;
        }
    }

}
