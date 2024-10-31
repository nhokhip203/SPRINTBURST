/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.utils;

import com.n2.sprintburst.service.LichSuHoaDonService;

/**
 *
 * @author Mtt
 */
public class Test {

    public static void main(String[] args) {
        System.err.println(new LichSuHoaDonService().getLichHoaDonByID(12).size());
    }
}
