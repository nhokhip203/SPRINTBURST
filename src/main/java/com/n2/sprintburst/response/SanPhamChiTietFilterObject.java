/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.response;

import com.n2.sprintburst.entity.ChatLieu;
import com.n2.sprintburst.entity.CoGiay;
import com.n2.sprintburst.entity.DeGiay;
import com.n2.sprintburst.entity.MauSac;
import com.n2.sprintburst.entity.Size;
import com.n2.sprintburst.entity.ThuongHieu;
import com.n2.sprintburst.entity.XuatXu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/**
 *
 * @author Mtt
 */
public class SanPhamChiTietFilterObject {

    private ThuongHieu thuongHieuFilter;
    private XuatXu xuatXuFilter;
    private ChatLieu chatLieuFilter;
    private DeGiay deGiayFilter;
    private CoGiay coGiayFilter;
    private MauSac mauSacFilter;
    private Size sizeFilter;
    private String keyword;
    private Integer minPrice;
    private Integer maxPrice;
    private boolean trangThai;
}
