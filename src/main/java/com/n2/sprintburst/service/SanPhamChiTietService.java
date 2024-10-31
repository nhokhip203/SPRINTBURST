/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.service;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.SanPham;
import com.n2.sprintburst.entity.SanPhamChiTiet;
import com.n2.sprintburst.response.SanPhamChiTietFilterObject;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Mtt
 */
public class SanPhamChiTietService {

    public static List<SanPhamChiTiet> getAllActive() {
        try {
            Session s = HibernateConfig.getSessionFactory().openSession();
            return s.createQuery("from SanPhamChiTiet where trangThai = :status", SanPhamChiTiet.class)
                    .setParameter("status", true)
                    .getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void addOrUpdate(SanPhamChiTiet spct) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {
                SanPhamChiTiet found = s.createSelectionQuery("from SanPhamChiTiet order by id desc", SanPhamChiTiet.class).setMaxResults(1).getSingleResultOrNull();

                int newId;
                if (found == null) {
                    newId = 1;
                } else {
                    newId = found.getId() + 1;
                }

                spct.setMaSanPhamChiTiet("SPCT" + newId);
                s.merge(spct);
                s.flush();
            });

        } catch (Exception e) {
            throw e;
        }
    }

    public static void delete(SanPhamChiTiet spct) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {
                spct.setTrangThai(false);
                s.merge(spct);
                s.flush();
            });

        } catch (Exception e) {
            throw e;
        }
    }

    public static SanPhamChiTiet findById(int id) {
        try {
            Session s = HibernateConfig.getSessionFactory().openSession();
            return s.createQuery("from SanPhamChiTiet where id = :id", SanPhamChiTiet.class)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        SanPham sp = new SanPham();

        spct.setSanPham(sp);
        sp.setId(1);

        addOrUpdate(spct);
    }

    public static List<SanPhamChiTiet> filterByFilterObj(SanPhamChiTietFilterObject filterObj) {
        try {
            Session s = HibernateConfig.getSessionFactory().openSession();
            StringBuilder query = new StringBuilder("from SanPhamChiTiet ");

            query.append("where trangThai = :trangThai ");
            if (filterObj.getThuongHieuFilter() != null) {
                query.append("and thuongHieu.id = :thuongHieuId ");
            }
            if (filterObj.getXuatXuFilter() != null) {
                query.append("and xuatXu.id = :xuatXuId ");
            }
            if (filterObj.getChatLieuFilter() != null) {
                query.append("and chatLieu.id = :chatLieuId ");
            }
            if (filterObj.getDeGiayFilter() != null) {
                query.append("and deGiay.id = :deGiayId ");
            }
            if (filterObj.getCoGiayFilter() != null) {
                query.append("and coGiay.id = :coGiayId ");
            }
            if (filterObj.getMauSacFilter() != null) {
                query.append("and mauSac.id = :mauSacId ");
            }
            if (filterObj.getSizeFilter() != null) {
                query.append("and size.id = :sizeId ");
            }
            if (!filterObj.getKeyword().isEmpty()) {
                query.append("and (sanpham.tenSanPham like :ten or tenSanPhamChiTiet like :ten) ");
            }

            query.append("and giaBan between :min and :max");

            Query q = s.createQuery(query.toString(), SanPhamChiTiet.class);

            q.setParameter("trangThai", filterObj.isTrangThai());
            if (filterObj.getThuongHieuFilter() != null) {
                q.setParameter("thuongHieuId", filterObj.getThuongHieuFilter().getId());
            }
            if (filterObj.getXuatXuFilter() != null) {
                q.setParameter("xuatXuId", filterObj.getXuatXuFilter().getId());
            }
            if (filterObj.getChatLieuFilter() != null) {
                q.setParameter("chatLieuId", filterObj.getChatLieuFilter().getId());
            }
            if (filterObj.getDeGiayFilter() != null) {
                q.setParameter("deGiayId", filterObj.getDeGiayFilter().getId());
            }
            if (filterObj.getCoGiayFilter() != null) {
                q.setParameter("coGiayId", filterObj.getCoGiayFilter().getId());
            }
            if (filterObj.getMauSacFilter() != null) {
                q.setParameter("mauSacId", filterObj.getMauSacFilter().getId());
            }
            if (filterObj.getSizeFilter() != null) {
                q.setParameter("sizeId", filterObj.getSizeFilter().getId());
            }
            if (!filterObj.getKeyword().isEmpty()) {
                q.setParameter("ten", "%" + filterObj.getKeyword() + "%");
            }
            q.setParameter("min", filterObj.getMinPrice());
            q.setParameter("max", filterObj.getMaxPrice());

            return q.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<SanPhamChiTiet> filterByKeyword(String keyword, Integer min, Integer max) {
        try {
            Session s = HibernateConfig.getSessionFactory().openSession();
            StringBuilder query = new StringBuilder("from SanPhamChiTiet "
                    + "where (giaBan between :min and :max "
                    + "and trangThai = true) "
                    + "and (thuongHieu.ten like :keyword "
                    + "or xuatXu.ten like :keyword "
                    + "or chatLieu.ten like :keyword "
                    + "or deGiay.ten like :keyword "
                    + "or coGiay.ten like :keyword "
                    + "or mauSac.ten like :keyword "
                    + "or size.ten like :keyword "
                    + "or tenSanPhamChiTiet like :keyword)"
            );

            Query q = s.createQuery(query.toString(), SanPhamChiTiet.class);
            q.setParameter("keyword", "%" + keyword + "%");
            q.setParameter("min", min);
            q.setParameter("max", max);

            return q.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

}
