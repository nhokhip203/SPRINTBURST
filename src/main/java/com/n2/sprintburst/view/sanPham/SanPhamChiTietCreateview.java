/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.n2.sprintburst.view.sanPham;

import com.formdev.flatlaf.FlatLightLaf;
import com.n2.sprintburst.entity.ChatLieu;
import com.n2.sprintburst.entity.CoGiay;
import com.n2.sprintburst.entity.DeGiay;
import com.n2.sprintburst.entity.MauSac;
import com.n2.sprintburst.entity.SanPham;
import com.n2.sprintburst.entity.SanPhamChiTiet;
import com.n2.sprintburst.entity.Size;
import com.n2.sprintburst.entity.ThuocTinh;
import com.n2.sprintburst.entity.ThuongHieu;
import com.n2.sprintburst.entity.XuatXu;
import com.n2.sprintburst.service.SanPhamChiTietService;
import com.n2.sprintburst.service.SanPhamService;
import com.n2.sprintburst.service.ThuocTinhService;
import com.n2.sprintburst.view.Home;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Mtt
 */
public class SanPhamChiTietCreateview extends javax.swing.JFrame implements SanPhamChiTietOperationForm {
//MODELS

    DefaultComboBoxModel cbxThuongHieuBoxModel;
    DefaultComboBoxModel cbxXuatXuBoxModel;
    DefaultComboBoxModel cbxChatLieuBoxModel;
    DefaultComboBoxModel cbxDeGiayBoxModel;
    DefaultComboBoxModel cbxCoGiayBoxModel;
    DefaultComboBoxModel cbxMauSacBoxModel;
    DefaultComboBoxModel cbxSizeBoxModel;
    DefaultComboBoxModel cbxSanPhamBoxModel;

    List<ThuocTinh> thuongHieuData;
    List<ThuocTinh> xuatXuData;
    List<ThuocTinh> chatLieuData;
    List<ThuocTinh> deGiayData;
    List<ThuocTinh> coGiayData;
    List<ThuocTinh> mauSacData;
    List<ThuocTinh> sizeData;
    List<SanPham> sanPhamData;

    SanPhamView parent;

    /**
     * Creates new form SanPhamChiTietCreateview
     */
    public SanPhamChiTietCreateview(SanPhamView parent) {
        FlatLightLaf.setup();
        initComponents();
        setFrameIcon();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.parent = parent;

        cbxThuongHieuBoxModel = (DefaultComboBoxModel) cbxThuongHieuSPCTCreate.getModel();
        cbxXuatXuBoxModel = (DefaultComboBoxModel) cbxXuatXuSPCTCreate.getModel();
        cbxChatLieuBoxModel = (DefaultComboBoxModel) cbxChatLieuSPCTCreate.getModel();
        cbxDeGiayBoxModel = (DefaultComboBoxModel) cbxDeGiaySPCTCreate.getModel();
        cbxCoGiayBoxModel = (DefaultComboBoxModel) cbxCoGiaySPCTCreate.getModel();
        cbxMauSacBoxModel = (DefaultComboBoxModel) cbxMauSacSPCTCreate.getModel();
        cbxSizeBoxModel = (DefaultComboBoxModel) cbxSizeSPCTCreate.getModel();
        cbxSanPhamBoxModel = (DefaultComboBoxModel) cbxSanPhamSPCTCreate.getModel();

        initForm();
    }

    private void setFrameIcon() {
        ImageIcon logoIcon = null;
        java.net.URL imgURL = Home.class.getResource("/icon/mini_logo.png");
        if (imgURL != null) {
            logoIcon = new ImageIcon(imgURL);
            this.setIconImage(logoIcon.getImage());
        } else {
            JOptionPane.showMessageDialog(this, "Icon image not found.");
        }
    }

    public void initForm() {
        initCbxData();
        initCbxUI();
        inittxtUI();

    }

    public void initCbxUI() {
        cbxThuongHieuSPCTCreate.setSelectedIndex(thuongHieuData.isEmpty() ? -1 : 0);
        cbxXuatXuSPCTCreate.setSelectedIndex(xuatXuData.isEmpty() ? -1 : 0);
        cbxChatLieuSPCTCreate.setSelectedIndex(chatLieuData.isEmpty() ? -1 : 0);
        cbxDeGiaySPCTCreate.setSelectedIndex(deGiayData.isEmpty() ? -1 : 0);
        cbxCoGiaySPCTCreate.setSelectedIndex(coGiayData.isEmpty() ? -1 : 0);
        cbxMauSacSPCTCreate.setSelectedIndex(mauSacData.isEmpty() ? -1 : 0);
        cbxSizeSPCTCreate.setSelectedIndex(sizeData.isEmpty() ? -1 : 0);
        cbxSanPhamSPCTCreate.setSelectedIndex(sanPhamData.isEmpty() ? -1 : 0);
    }

    public void inittxtUI() {
        txtPriceSPCTCreate.setText("");
        txtSoLuongSPCTCreate.setText("");
        txtTenSPCTCreate.setText("");

    }

    public void initCbxData() {
        thuongHieuData = ThuocTinhService.getThuocTinhByTableName("ThuongHieu");
        xuatXuData = ThuocTinhService.getThuocTinhByTableName("XuatXu");
        chatLieuData = ThuocTinhService.getThuocTinhByTableName("ChatLieu");
        deGiayData = ThuocTinhService.getThuocTinhByTableName("DeGiay");
        coGiayData = ThuocTinhService.getThuocTinhByTableName("CoGiay");
        mauSacData = ThuocTinhService.getThuocTinhByTableName("MauSac");
        sizeData = ThuocTinhService.getThuocTinhByTableName("Size");
        sanPhamData = SanPhamService.getAllActive();

//        Collections.reverse(thuongHieuData);
//        Collections.reverse(xuatXuData);
//        Collections.reverse(chatLieuData);
//        Collections.reverse(deGiayData);
//        Collections.reverse(coGiayData);
//        Collections.reverse(mauSacData);
//        Collections.reverse(sizeData);
//        Collections.reverse(sanPhamData);
        cbxThuongHieuBoxModel.removeAllElements();
        cbxXuatXuBoxModel.removeAllElements();
        cbxChatLieuBoxModel.removeAllElements();
        cbxDeGiayBoxModel.removeAllElements();
        cbxCoGiayBoxModel.removeAllElements();
        cbxMauSacBoxModel.removeAllElements();
        cbxSizeBoxModel.removeAllElements();
        cbxSanPhamBoxModel.removeAllElements();

        cbxThuongHieuBoxModel.addAll(thuongHieuData.stream().map(e -> e.getTen()).toList());
        cbxXuatXuBoxModel.addAll(xuatXuData.stream().map(e -> e.getTen()).toList());
        cbxChatLieuBoxModel.addAll(chatLieuData.stream().map(e -> e.getTen()).toList());
        cbxDeGiayBoxModel.addAll(deGiayData.stream().map(e -> e.getTen()).toList());
        cbxCoGiayBoxModel.addAll(coGiayData.stream().map(e -> e.getTen()).toList());
        cbxMauSacBoxModel.addAll(mauSacData.stream().map(e -> e.getTen()).toList());
        cbxSizeBoxModel.addAll(sizeData.stream().map(e -> e.getTen()).toList());
        cbxSanPhamBoxModel.addAll(sanPhamData.stream().map(e -> e.getTenSanPham()).toList());

    }

    private SanPhamChiTiet parseForm() {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        SanPham sp = new SanPham();
        ThuongHieu th = new ThuongHieu();
        XuatXu xx = new XuatXu();
        ChatLieu cl = new ChatLieu();
        DeGiay dg = new DeGiay();
        CoGiay cg = new CoGiay();
        MauSac ms = new MauSac();
        Size size = new Size();

        spct.setTenSanPhamChiTiet(txtTenSPCTCreate.getText());
        spct.setNgayTao(LocalDateTime.now());
        spct.setNgayCapNhat(LocalDateTime.now());
        spct.setTrangThai(true);
        try {
            spct.setGiaBan(Integer.parseInt(txtPriceSPCTCreate.getText()));
            spct.setSoLuong(Integer.parseInt(txtSoLuongSPCTCreate.getText()));
        } catch (Exception e) {
            throw new RuntimeException("Phai la so");

        }
        spct.setSanPham(sanPhamData.isEmpty() ? null : sp);
        spct.setThuongHieu(thuongHieuData.isEmpty() ? null : th);
        spct.setXuatXu(xuatXuData.isEmpty() ? null : xx);
        spct.setChatLieu(chatLieuData.isEmpty() ? null : cl);
        spct.setDeGiay(deGiayData.isEmpty() ? null : dg);
        spct.setCoGiay(coGiayData.isEmpty() ? null : cg);
        spct.setMauSac(mauSacData.isEmpty() ? null : ms);
        spct.setSize(sizeData.isEmpty() ? null : size);

        if (!sanPhamData.isEmpty()) {
            sp.setId(sanPhamData.get(cbxSanPhamSPCTCreate.getSelectedIndex()).getId());
        }
        if (!thuongHieuData.isEmpty()) {
            th.setId(thuongHieuData.get(cbxThuongHieuSPCTCreate.getSelectedIndex()).getId());
        }
        if (!xuatXuData.isEmpty()) {
            xx.setId(xuatXuData.get(cbxXuatXuSPCTCreate.getSelectedIndex()).getId());
        }
        if (!chatLieuData.isEmpty()) {
            cl.setId(chatLieuData.get(cbxChatLieuSPCTCreate.getSelectedIndex()).getId());
        }
        if (!deGiayData.isEmpty()) {
            dg.setId(deGiayData.get(cbxDeGiaySPCTCreate.getSelectedIndex()).getId());
        }
        if (!coGiayData.isEmpty()) {
            cg.setId(coGiayData.get(cbxCoGiaySPCTCreate.getSelectedIndex()).getId());
        }
        if (!mauSacData.isEmpty()) {
            ms.setId(mauSacData.get(cbxMauSacSPCTCreate.getSelectedIndex()).getId());
        }
        if (!sizeData.isEmpty()) {
            size.setId(sizeData.get(cbxSizeSPCTCreate.getSelectedIndex()).getId());
        }

        return spct;

    }

    private void displayThuocTinhCreateWindow(String tbl) {
        new CreateThuocTinhForm(tbl, this).setVisible(true);

    }

    private void addSanPhamChiTiet() {
        SanPhamChiTietService.addOrUpdate(parseForm());
        parent.initOrRefreshStateSanPhamChiTiet();
        parent.renderSanPhamChiTietTable();
        this.dispose();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxSanPhamSPCTCreate = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPriceSPCTCreate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoLuongSPCTCreate = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbxThuongHieuSPCTCreate = new javax.swing.JComboBox<>();
        cbxChatLieuSPCTCreate = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbxCoGiaySPCTCreate = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxSizeSPCTCreate = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxXuatXuSPCTCreate = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbxDeGiaySPCTCreate = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbxMauSacSPCTCreate = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnCreateThuongHieuSPCT = new javax.swing.JButton();
        btnCreateXuatXuSPCT = new javax.swing.JButton();
        btnCreateCoGiaySPCT = new javax.swing.JButton();
        btnDeGiayCreateSPCT = new javax.swing.JButton();
        btnCreateChatLieuSPCT = new javax.swing.JButton();
        btnCreateSizeSPCT = new javax.swing.JButton();
        btnCreateMauSacSPCT = new javax.swing.JButton();
        btnCloseSPCTCreate = new javax.swing.JButton();
        btnCreateSPCT = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtTenSPCTCreate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thêm chi tết sản phẩm - SPRINTBURST");
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 0));
        setPreferredSize(new java.awt.Dimension(900, 400));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("THÊM SẢN PHẨM CHI TIẾT");

        jLabel1.setText("Sản phẩm");

        cbxSanPhamSPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Giá bán");

        jLabel3.setText("Số lượng");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Thương hiệu");

        cbxThuongHieuSPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxChatLieuSPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Chất liệu");

        cbxCoGiaySPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Cổ giày");

        cbxSizeSPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Size");

        cbxXuatXuSPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Xuất xứ");

        cbxDeGiaySPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Đế giày");

        cbxMauSacSPCTCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Màu sắc");

        btnCreateThuongHieuSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateThuongHieuSPCT.setText("+");
        btnCreateThuongHieuSPCT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreateThuongHieuSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateThuongHieuSPCTActionPerformed(evt);
            }
        });

        btnCreateXuatXuSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateXuatXuSPCT.setText("+");
        btnCreateXuatXuSPCT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreateXuatXuSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateXuatXuSPCTActionPerformed(evt);
            }
        });

        btnCreateCoGiaySPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateCoGiaySPCT.setText("+");
        btnCreateCoGiaySPCT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreateCoGiaySPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateCoGiaySPCTActionPerformed(evt);
            }
        });

        btnDeGiayCreateSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeGiayCreateSPCT.setText("+");
        btnDeGiayCreateSPCT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeGiayCreateSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeGiayCreateSPCTActionPerformed(evt);
            }
        });

        btnCreateChatLieuSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateChatLieuSPCT.setText("+");
        btnCreateChatLieuSPCT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreateChatLieuSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateChatLieuSPCTActionPerformed(evt);
            }
        });

        btnCreateSizeSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateSizeSPCT.setText("+");
        btnCreateSizeSPCT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreateSizeSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateSizeSPCTActionPerformed(evt);
            }
        });

        btnCreateMauSacSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateMauSacSPCT.setText("+");
        btnCreateMauSacSPCT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreateMauSacSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateMauSacSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbxXuatXuSPCTCreate, 0, 78, Short.MAX_VALUE)
                    .addComponent(cbxThuongHieuSPCTCreate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateThuongHieuSPCT)
                    .addComponent(btnCreateXuatXuSPCT))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxChatLieuSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateChatLieuSPCT))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDeGiaySPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeGiayCreateSPCT)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMauSacSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateMauSacSPCT))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCoGiaySPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateCoGiaySPCT)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSizeSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateSizeSPCT)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbxCoGiaySPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cbxSizeSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateCoGiaySPCT)
                            .addComponent(btnCreateChatLieuSPCT)
                            .addComponent(btnCreateSizeSPCT))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbxMauSacSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateMauSacSPCT)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxThuongHieuSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cbxChatLieuSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateThuongHieuSPCT))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbxXuatXuSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cbxDeGiaySPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateXuatXuSPCT)
                            .addComponent(btnDeGiayCreateSPCT))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btnCloseSPCTCreate.setText("Quay lại");
        btnCloseSPCTCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseSPCTCreateActionPerformed(evt);
            }
        });

        btnCreateSPCT.setText("Tạo");
        btnCreateSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateSPCTActionPerformed(evt);
            }
        });

        jLabel12.setText("Tên spct");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCloseSPCTCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateSPCT))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSanPhamSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPriceSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuongSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtPriceSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtTenSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cbxSanPhamSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtSoLuongSPCTCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCloseSPCTCreate)
                    .addComponent(btnCreateSPCT))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateChatLieuSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateChatLieuSPCTActionPerformed
        displayThuocTinhCreateWindow("ChatLieu");    }//GEN-LAST:event_btnCreateChatLieuSPCTActionPerformed

    private void btnCreateSizeSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateSizeSPCTActionPerformed
        displayThuocTinhCreateWindow("Size");
    }//GEN-LAST:event_btnCreateSizeSPCTActionPerformed

    private void btnCloseSPCTCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseSPCTCreateActionPerformed
        this.dispose();    }//GEN-LAST:event_btnCloseSPCTCreateActionPerformed

    private void btnCreateSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateSPCTActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Add?", "Add?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        addSanPhamChiTiet();
    }//GEN-LAST:event_btnCreateSPCTActionPerformed

    private void btnCreateThuongHieuSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateThuongHieuSPCTActionPerformed
        displayThuocTinhCreateWindow("ThuongHieu");
    }//GEN-LAST:event_btnCreateThuongHieuSPCTActionPerformed

    private void btnCreateXuatXuSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateXuatXuSPCTActionPerformed
        displayThuocTinhCreateWindow("XuatXu");    }//GEN-LAST:event_btnCreateXuatXuSPCTActionPerformed

    private void btnDeGiayCreateSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeGiayCreateSPCTActionPerformed
        displayThuocTinhCreateWindow("DeGiay");     }//GEN-LAST:event_btnDeGiayCreateSPCTActionPerformed

    private void btnCreateCoGiaySPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateCoGiaySPCTActionPerformed
        displayThuocTinhCreateWindow("CoGiay");
    }//GEN-LAST:event_btnCreateCoGiaySPCTActionPerformed

    private void btnCreateMauSacSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateMauSacSPCTActionPerformed
        displayThuocTinhCreateWindow("MauSac");
    }//GEN-LAST:event_btnCreateMauSacSPCTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SanPhamChiTietCreateview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamChiTietCreateview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamChiTietCreateview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamChiTietCreateview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamChiTietCreateview(new SanPhamView()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseSPCTCreate;
    private javax.swing.JButton btnCreateChatLieuSPCT;
    private javax.swing.JButton btnCreateCoGiaySPCT;
    private javax.swing.JButton btnCreateMauSacSPCT;
    private javax.swing.JButton btnCreateSPCT;
    private javax.swing.JButton btnCreateSizeSPCT;
    private javax.swing.JButton btnCreateThuongHieuSPCT;
    private javax.swing.JButton btnCreateXuatXuSPCT;
    private javax.swing.JButton btnDeGiayCreateSPCT;
    private javax.swing.JComboBox<String> cbxChatLieuSPCTCreate;
    private javax.swing.JComboBox<String> cbxCoGiaySPCTCreate;
    private javax.swing.JComboBox<String> cbxDeGiaySPCTCreate;
    private javax.swing.JComboBox<String> cbxMauSacSPCTCreate;
    private javax.swing.JComboBox<String> cbxSanPhamSPCTCreate;
    private javax.swing.JComboBox<String> cbxSizeSPCTCreate;
    private javax.swing.JComboBox<String> cbxThuongHieuSPCTCreate;
    private javax.swing.JComboBox<String> cbxXuatXuSPCTCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtPriceSPCTCreate;
    private javax.swing.JTextField txtSoLuongSPCTCreate;
    private javax.swing.JTextField txtTenSPCTCreate;
    // End of variables declaration//GEN-END:variables
}
