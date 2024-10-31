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
import java.util.List;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "SanPham")

public class SanPham {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tenSanPham")
    @NaturalId(mutable = true)
    private String tenSanPham;

    @Column(name = "maSanPham")
    @NaturalId
    private String maSanPham;

    @Column(name = "ngayTao")
    private LocalDateTime ngayTao;

    @Column(name = "ngayCapNhat")
    private LocalDateTime ngayCapNhat;

    @Column(name = "ngayXoa")
    private LocalDateTime ngayXoa;

    @Column(name = "trangThai")
    private boolean trangThai;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sanPham", fetch = FetchType.EAGER)
    List<SanPhamChiTiet> sanPhamChiTiets;
}
