package com.n2.sprintburst.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "soLuong")
    private int soLuong;

    @Column(name = "giaBan")
    private int giaBan;

    @Column(name = "trangThai")
    private boolean trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHoaDon", referencedColumnName = "id")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSanPhamChiTiet", referencedColumnName = "id")
    private SanPhamChiTiet sanPhamChiTiet;
    
    public Object[] toDataRow(){
        return new Object[]{id,hoaDon.getMaHoaDon(),sanPhamChiTiet.getMaSanPhamChiTiet(),soLuong,giaBan,trangThai};
    }
}
