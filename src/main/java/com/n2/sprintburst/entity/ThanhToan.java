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
@Table(name = "ThanhToan")
public class ThanhToan {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ngayThanhToan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "soTienMat")
    private int soTienMat;

    @Column(name = "soTienChuyen")
    private int soTienChuyen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHoaDon", referencedColumnName = "id")
    private HoaDon hoaDon;
}
