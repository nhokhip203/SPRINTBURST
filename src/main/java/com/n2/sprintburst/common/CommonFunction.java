/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.common;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Admin
 */
public class CommonFunction {
    public LocalDateTime convertDatrSqlToLCT(Date dateSql) {
        Date sqlDate = new Date(System.currentTimeMillis());

        // Chuyển đổi sang LocalDate
        LocalDate localDate = sqlDate.toLocalDate();

        // Tạo LocalTime (ví dụ: đặt thời gian là 10:30 AM)
        LocalTime localTime = LocalTime.of(10, 30);

        // Kết hợp LocalDate và LocalTime thành LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        return localDateTime;
    }
}
