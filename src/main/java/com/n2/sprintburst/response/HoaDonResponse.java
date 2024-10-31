/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author NhokHip
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HoaDonResponse {
    private int id;
    private String maHoaDon;
    private LocalDateTime ngayTao;
    private int tongTruocGiamGia;
    private int tongSauGiamGia;
    private String ghiChu;
    private String tenNguoiNhan;
    private String dienThoaiNguoiNhan;
    private String diaChiNguoiNhan;
    private String idKhachHang;
    private String idNhanVien;
    private String idtrangThaiHoaDon;
    private String idphieuGiamGia;
}
