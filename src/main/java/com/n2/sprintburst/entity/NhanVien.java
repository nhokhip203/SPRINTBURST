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
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "maTaiKhoan")

    private String maTaiKhoan;

    @Column(name = "email")

    private String email;

    @Column(name = "dienThoai")

    private String dienThoai;

    @Column(name = "password")
    private String password;

    @Column(name = "hoTen")
    private String hoTen;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "ghiChu")
    private String ghiChu;

    @Column(name = "laQuanLy")
    private boolean laQuanLy;

    @Column(name = "ngayTao")
    @CreationTimestamp
    private LocalDateTime ngayTao;

    @Column(name = "ngayCapNhat")
    @UpdateTimestamp
    private LocalDateTime ngayCapNhat;

    @Column(name = "ngayXoa")
    @UpdateTimestamp
    private LocalDateTime ngayXoa;

    @Column(name = "trangThai")
    private boolean trangThai;

    @Override
    public String toString() {
        return hoTen;
    }

}
