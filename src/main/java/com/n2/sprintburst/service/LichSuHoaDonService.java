/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.service;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.HoaDon;
import com.n2.sprintburst.entity.LichSuHoaDon;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author NhokHip
 */
public class LichSuHoaDonService {

    public Session session;

    public List<LichSuHoaDon> getAllLichSu() {
        session = HibernateConfig.getSessionFactory().openSession();
        List<LichSuHoaDon> list = session.createQuery("from LichSuHoaDon", LichSuHoaDon.class).list();
        session.close();//        

        return list;
    }

    public List<LichSuHoaDon> getLichHoaDonByID(int idHoaDon) {
        session = HibernateConfig.getSessionFactory().openSession();
        List<LichSuHoaDon> list = session.createQuery("from LichSuHoaDon where hoaDon.id = :id ", LichSuHoaDon.class)
                .setParameter("id", idHoaDon)
                .list();
        session.close();//        

        return list;
    }
    public List<LichSuHoaDon> getLichSuHoaDonBtMa(String maHoaDon){
         session = HibernateConfig.getSessionFactory().openSession();
        List<LichSuHoaDon> list = session.createQuery("from LichSuHoaDon where hoaDon.maHoaDon = :ma ", LichSuHoaDon.class)
                .setParameter("ma", maHoaDon)
                .list();
        session.close();//        

        return list;
    }
}
