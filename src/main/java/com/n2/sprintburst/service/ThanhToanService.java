/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.service;

import com.n2.sprintburst.config.HibernateConfig;
import com.n2.sprintburst.entity.ThanhToan;

/**
 *
 * @author Mtt
 */
public class ThanhToanService {

    public static void create(ThanhToan th) {
        try {
            HibernateConfig.getSessionFactory().inTransaction(s -> {
                s.persist(th);
                s.flush();
            });
        } catch (Exception e) {
            throw e;
        }
    }

}
