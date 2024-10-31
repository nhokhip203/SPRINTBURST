package com.n2.sprintburst.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "SanPhamChiTiet")
public class SanPhamChiTiet {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "maSanPhamChiTiet")
    private String maSanPhamChiTiet;

    @Column(name = "tenSanPhamChiTiet")
    private String tenSanPhamChiTiet;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "ngayTao")
    private LocalDateTime ngayTao;

    @Column(name = "ngayCapNhat")
    private LocalDateTime ngayCapNhat;

    @Column(name = "ngayXoa")
    private LocalDateTime ngayXoa;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Column(name = "giaBan")
    private int giaBan;

    @Column(name = "soLuong")
    private int soLuong;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSanPham", referencedColumnName = "id")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idThuongHieu", referencedColumnName = "id")
    private ThuongHieu thuongHieu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idXuatXu", referencedColumnName = "id")
    private XuatXu xuatXu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idChatLieu", referencedColumnName = "id")
    private ChatLieu chatLieu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDeGiay", referencedColumnName = "id")
    private DeGiay deGiay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCoGiay", referencedColumnName = "id")
    private CoGiay coGiay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMauSac", referencedColumnName = "id")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSize", referencedColumnName = "id")
    private Size size;

   

}
