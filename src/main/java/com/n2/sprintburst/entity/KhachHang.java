package com.n2.sprintburst.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "maKhachHang")
    private String maKhachHang;

    @Column(name = "tenKhachHang")
    private String tenKhachHang;

    @Column(name = "email")
    private String email;

    @Column(name = "dienThoai")
    
    private String dienThoai;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "ghiChu")
    private String ghiChu;

    @Column(name = "ngayTao")
    @CreationTimestamp
    private LocalDateTime ngayTao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idNhanVien", referencedColumnName = "id")
    private NhanVien nhanVien;

    
}

