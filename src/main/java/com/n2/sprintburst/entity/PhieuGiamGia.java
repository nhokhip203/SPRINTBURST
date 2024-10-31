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
@Table(name = "PhieuGiamGia")

public class PhieuGiamGia {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "maGiamGia")
    private String maGiamGia;

    @Column(name = "tenGiamGia")
    private String tenGiamGia;

    @Column(name = "giaTriToiThieu")
    private int giaTriToiThieu;

    @Column(name = "giamPhanTram")
    private double giamPhanTram;

    @Column(name = "soGiamToiDa")
    private int soGiamToiDa;

    @Column(name = "ngayBatDau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngayKetThuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "ngayTao")
    private LocalDateTime ngayTao;

    @Column(name = "trangThai")
    private boolean trangThai;
}
