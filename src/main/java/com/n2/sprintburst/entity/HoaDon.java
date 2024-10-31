package com.n2.sprintburst.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "maHoaDon")
    @NaturalId
    private String maHoaDon;

    @Column(name = "ngayTao")
    private LocalDateTime ngayTao;

    @Column(name = "tongTruocGiamGia")
    private int tongTruocGiamGia;

    @Column(name = "tongSauGiamGia")
    private int tongSauGiamGia;

    @Column(name = "ghiChu")
    private String ghiChu;

    @Column(name = "tenNguoiNhan")
    private String tenNguoiNhan;

    @Column(name = "dienThoaiNguoiNhan")
    private String dienThoaiNguoiNhan;

    @Column(name = "diaChiNguoiNhan")
    private String diaChiNguoiNhan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idKhachHang", referencedColumnName = "id")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idNhanVien", referencedColumnName = "id")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTrangThaiHoaDon", referencedColumnName = "id")
    private TrangThaiHoaDon trangThaiHoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPhieuGiamGia", referencedColumnName = "id")
    private PhieuGiamGia phieuGiamGia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hoaDon", fetch = FetchType.EAGER)
    List<HoaDonChiTiet> hoaDonChiTiets;
}
