/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.service;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.ChatLieu;
import com.n2.sprintburst.entity.CoGiay;
import com.n2.sprintburst.entity.DeGiay;
import com.n2.sprintburst.entity.MauSac;
import com.n2.sprintburst.entity.Size;
import com.n2.sprintburst.entity.ThuocTinh;
import com.n2.sprintburst.entity.ThuongHieu;
import com.n2.sprintburst.entity.XuatXu;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Mtt
 */
public class ThuocTinhService {

    public static List<ThuocTinh> getThuocTinhByTableName(String tbl) {
        try {
            Session s = HibernateConfig.getSessionFactory().openSession();

            String query = "from " + tbl;
            System.out.println(query);
            Class classType;

            switch (tbl) {
                case "ThuongHieu":
                    classType = ThuongHieu.class;
                    break;
                case "XuatXu":
                    classType = XuatXu.class;
                    break;
                case "ChatLieu":
                    classType = ChatLieu.class;
                    break;
                case "DeGiay":
                    classType = DeGiay.class;
                    break;
                case "CoGiay":
                    classType = CoGiay.class;
                    break;
                case "MauSac":
                    classType = MauSac.class;
                    break;
                default:
                    classType = Size.class;
                    break;
            }
            return s.createSelectionQuery(query, classType).getResultList();
        } catch (Exception e) {
            throw e;
        }

    }

    public static void add(String ten, String tbl) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {
                switch (tbl) {
                    case "ThuongHieu":
                        ThuongHieu newTt = new ThuongHieu();
                        newTt.setTen(ten);
                        s.persist(newTt);
                        break;
                    case "XuatXu":
                        XuatXu newTt2 = new XuatXu();
                        newTt2.setTen(ten);
                        s.persist(newTt2);
                        break;
                    case "ChatLieu":
                        ChatLieu newTt3 = new ChatLieu();
                        newTt3.setTen(ten);
                        s.persist(newTt3);
                        break;
                    case "DeGiay":
                        DeGiay newTt4 = new DeGiay();
                        newTt4.setTen(ten);
                        s.persist(newTt4);
                        break;
                    case "CoGiay":
                        CoGiay newTt5 = new CoGiay();
                        newTt5.setTen(ten);
                        s.persist(newTt5);
                        break;
                    case "MauSac":
                        MauSac newTt6 = new MauSac();
                        newTt6.setTen(ten);
                        s.persist(newTt6);
                        break;
                    default:
                        Size newTt7 = new Size();
                        newTt7.setTen(ten);
                        s.persist(newTt7);
                        break;
                }

                s.flush();
            });

        } catch (Exception e) {
            throw e;
        }
    }

    public static void delete(String ten, String tbl) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {

                String sql = "delete from " + tbl + " where ten like :ten";
                s.createQuery(sql)
                        .setParameter("ten", ten).executeUpdate();

                s.flush();
            });

        } catch (Exception e) {
            throw e;
        }
    }
}
