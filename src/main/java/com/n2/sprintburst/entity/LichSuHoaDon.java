package com.n2.sprintburst.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "LichSuHoaDon")
public class LichSuHoaDon {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ngayTacDong")
    private LocalDateTime ngayTacDong;

    @Column(name = "ghiChu")
    private String ghiChu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHoaDon", referencedColumnName = "id")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idNhanVien", referencedColumnName = "id")
    private NhanVien nhanVien;
    
    public Object[] toDataRow(){
        return new Object[]{id,hoaDon.getMaHoaDon(),nhanVien.getHoTen(),ghiChu,ngayTacDong};
    }
}
