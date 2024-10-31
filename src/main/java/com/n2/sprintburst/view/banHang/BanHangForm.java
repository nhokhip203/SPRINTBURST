/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.n2.sprintburst.view.banHang;

import com.formdev.flatlaf.FlatLightLaf;
import com.n2.sprintburst.entity.HoaDon;
import com.n2.sprintburst.entity.HoaDonChiTiet;
import com.n2.sprintburst.entity.KhachHang;
import com.n2.sprintburst.entity.NhanVien;
import com.n2.sprintburst.entity.SanPhamChiTiet;
import com.n2.sprintburst.entity.ThanhToan;
import com.n2.sprintburst.service.HoaDonChiTietService;
import com.n2.sprintburst.service.HoaDonService;
import com.n2.sprintburst.service.KhachHangService;
import com.n2.sprintburst.service.SanPhamChiTietService;
import com.n2.sprintburst.service.ThanhToanService;
import com.n2.sprintburst.utils.QRCodeScannerForBanHang;
import com.n2.sprintburst.view.Home;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mtt
 */
public class BanHangForm extends javax.swing.JPanel {

    DefaultTableModel spctTableModel;
    DefaultTableModel hoaDonTableModel;
    DefaultTableModel gioHangTableModel;

    List<SanPhamChiTiet> spctState;
    List<HoaDon> hoaDonState;
    HoaDon chosenHoaDonState;

    KhachHang khachHangState;

    NhanVien userState;

    Home home;

    public BanHangForm(NhanVien user, Home parent) {

        FlatLightLaf.setup();
        initComponents();

        this.userState = user;
        this.home = parent;

        spctTableModel = (DefaultTableModel) tblSanPhamChiTiet.getModel();
        hoaDonTableModel = (DefaultTableModel) tblHoaDon.getModel();
        gioHangTableModel = (DefaultTableModel) tblGioHang.getModel();

        initHoaDonState();
        initSPCTState();

        renderHoaDonTable();
        renderSPCTTable();

    }

    //STATES
    private void initSPCTState() {
        spctState = SanPhamChiTietService.getAllActive();
    }

    private void initHoaDonState() {
        hoaDonState = HoaDonService.getAllUnprocessed();
        Collections.reverse(hoaDonState);
    }

    private void initChosenHoaDonState() {
        int idx = tblHoaDon.getSelectedRow();

        if (idx == -1) {
            return;
        }
        HoaDonService.autoUpdateFields(hoaDonState.get(idx));
        chosenHoaDonState = hoaDonState.get(idx);
    }

    //UI
    private void renderHoaDonTable() {
        hoaDonTableModel.setRowCount(0);

        for (int i = 0; i < hoaDonState.size(); i++) {
            HoaDon hd = hoaDonState.get(i);
            hoaDonTableModel.addRow(new Object[]{
                i + 1,
                hd.getMaHoaDon(),
                hd.getNgayTao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh:mm")),
                hd.getTongSauGiamGia(),
                hd.getKhachHang() == null ? null : hd.getKhachHang().getTenKhachHang(),
                hd.getKhachHang() == null ? null : hd.getKhachHang().getDienThoai(),
                hd.getTrangThaiHoaDon() != null ? hd.getTrangThaiHoaDon().getTen() : null
            }
            );
        }
    }

    private void renderSPCTTable() {
        spctTableModel.setRowCount(0);
        for (int i = 0; i < spctState.size(); i++) {
            SanPhamChiTiet sp = spctState.get(i);
            spctTableModel.addRow(new Object[]{
                i + 1,
                sp.getMaSanPhamChiTiet(),
                sp.getSanPham() != null ? sp.getSanPham().getTenSanPham() : null,
                sp.getTenSanPhamChiTiet(),
                sp.getThuongHieu() != null ? sp.getThuongHieu().getTen() : null,
                sp.getXuatXu() != null ? sp.getXuatXu().getTen() : null,
                sp.getChatLieu() != null ? sp.getChatLieu().getTen() : null,
                sp.getDeGiay() != null ? sp.getDeGiay().getTen() : null,
                sp.getCoGiay() != null ? sp.getCoGiay().getTen() : null,
                sp.getMauSac() != null ? sp.getMauSac().getTen() : null,
                sp.getSize() != null ? sp.getSize().getTen() : null,
                sp.getSoLuong(),
                sp.getGiaBan()
            });
        }
    }

    private void renderGioHangTable() {
        gioHangTableModel.setRowCount(0);

        if (chosenHoaDonState == null) {
            return;
        }
        for (int i = 0; i < chosenHoaDonState.getHoaDonChiTiets().size(); i++) {
            HoaDonChiTiet hdct = chosenHoaDonState.getHoaDonChiTiets().get(i);
            gioHangTableModel.addRow(new Object[]{
                i + 1,
                hdct.getSanPhamChiTiet().getMaSanPhamChiTiet(),
                hdct.getSanPhamChiTiet().getSanPham().getTenSanPham(),
                hdct.getSanPhamChiTiet().getTenSanPhamChiTiet(),
                hdct.getSanPhamChiTiet().getThuongHieu() != null ? hdct.getSanPhamChiTiet().getThuongHieu().getTen() : null,
                hdct.getSanPhamChiTiet().getXuatXu() != null ? hdct.getSanPhamChiTiet().getXuatXu().getTen() : null,
                hdct.getSanPhamChiTiet().getChatLieu() != null ? hdct.getSanPhamChiTiet().getChatLieu().getTen() : null,
                hdct.getSanPhamChiTiet().getDeGiay() != null ? hdct.getSanPhamChiTiet().getDeGiay().getTen() : null,
                hdct.getSanPhamChiTiet().getCoGiay() != null ? hdct.getSanPhamChiTiet().getCoGiay().getTen() : null,
                hdct.getSanPhamChiTiet().getMauSac() != null ? hdct.getSanPhamChiTiet().getMauSac().getTen() : null,
                hdct.getSanPhamChiTiet().getSize() != null ? hdct.getSanPhamChiTiet().getSize().getTen() : null,
                hdct.getSoLuong(),
                hdct.getGiaBan(),
                hdct.getSoLuong() * hdct.getGiaBan()
            });
        }
    }

    private void renderChosenHoaDon() {
        try {
            if (chosenHoaDonState == null) {
                chosenHoaDonState = new HoaDon();
                chosenHoaDonState.setHoaDonChiTiets(new ArrayList<>());
            }
            initHoaDonState();
            initChosenHoaDonState();
            renderGioHangTable();

            lblHoaDonMa.setText(chosenHoaDonState.getMaHoaDon());
            lblTienTong.setText(new DecimalFormat("###,###,###").format(chosenHoaDonState.getTongTruocGiamGia()));
            lblThanhTien.setText(new DecimalFormat("###,###,###").format(chosenHoaDonState.getTongSauGiamGia()));
            lblTienGiam.setText(String.valueOf(chosenHoaDonState.getTongTruocGiamGia() - chosenHoaDonState.getTongSauGiamGia()));

            lblKhachHangTen.setText(chosenHoaDonState.getKhachHang() == null ? null : chosenHoaDonState.getKhachHang().getTenKhachHang());
            lblKhachHangDienThoai.setText(chosenHoaDonState.getKhachHang() == null ? null : chosenHoaDonState.getKhachHang().getDienThoai());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void refreshSPCTGroup() {
        initSPCTState();
        renderSPCTTable();
    }

    private void refreshHoaDonGroup() {
        initHoaDonState();
        renderHoaDonTable();

        txtThanhToanTienMat.setText("0");
        txtThanhToanTienChuyen.setText("0");

    }

    private void refreshChosenHoaDonGroup() {
        chosenHoaDonState = null;
        renderChosenHoaDon();
        gioHangTableModel.setRowCount(0);
    }

    private void refreshStatesAndTables() {
        refreshSPCTGroup();
        refreshHoaDonGroup();
        refreshChosenHoaDonGroup();
    }

    //INTERACTONS
    private void createHoaDon() {
        try {
            HoaDonService.add(new HoaDon(), userState);
            refreshStatesAndTables();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void addSPCTToHDCT(int num) {
        try {
            int idx = tblSanPhamChiTiet.getSelectedRow();

            if (idx == -1) {
                return;
            }

            int hdIdx = tblHoaDon.getSelectedRow();

            SanPhamChiTiet spct = spctState.get(idx);

            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setGiaBan(spct.getGiaBan());
            hdct.setHoaDon(chosenHoaDonState);
            hdct.setSanPhamChiTiet(spct);
            hdct.setSoLuong(1);
            hdct.setTrangThai(true);

            for (int i = 0; i < num; i++) {
                HoaDonChiTietService.add(hdct);
            }

            initChosenHoaDonState();
            renderChosenHoaDon();
            initHoaDonState();
            renderHoaDonTable();
            initSPCTState();
            renderSPCTTable();

            tblHoaDon.setRowSelectionInterval(hdIdx, hdIdx);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void removeSPCTFromGioHang() {
        try {
            int idx = tblGioHang.getSelectedRow();

            HoaDonChiTietService.remove(chosenHoaDonState.getHoaDonChiTiets().get(idx));
            initChosenHoaDonState();
            renderChosenHoaDon();
            initSPCTState();
            renderSPCTTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void createThanhToan() {
        ThanhToan th = new ThanhToan();
        th.setHoaDon(chosenHoaDonState);
        th.setSoTienMat(Integer.parseInt(txtThanhToanTienMat.getText()));
        th.setSoTienChuyen(Integer.parseInt(txtThanhToanTienChuyen.getText()));
        th.setNgayThanhToan(LocalDateTime.now());

        ThanhToanService.create(th);

        initChosenHoaDonState();

    }

    private void completeHoaDon() {
        try {
            if (JOptionPane.showConfirmDialog(this, "Thanh toan?", "Thanh toan", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
                return;
            }

            createThanhToan();
            HoaDon toComplete = chosenHoaDonState;
            toComplete.setTenNguoiNhan(txtNguoiNhanTen.getText());
            toComplete.setDienThoaiNguoiNhan(txtNguoiNhanSdt.getText());

            HoaDonService.complete(toComplete, userState);
            initChosenHoaDonState();
            JOptionPane.showMessageDialog(this, "Đã thanh toán");

            refreshStatesAndTables();

            home.setHoaDonFormVisible();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void scanQRCode() {
        new QRCodeScannerForBanHang(this).setVisible(true);

    }

    public void addParsedSPCTToGioHang(int id) {
        try {

            int idx = spctState.indexOf(spctState.stream().filter(spct -> spct.getId() == id).findFirst().get());

            if (idx == -1) {
                return;
            }
            SanPhamChiTiet spct = SanPhamChiTietService.findById(id);

            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setGiaBan(spct.getGiaBan());
            hdct.setHoaDon(chosenHoaDonState);
            hdct.setSanPhamChiTiet(spct);
            hdct.setSoLuong(1);
            hdct.setTrangThai(true);

            HoaDonChiTietService.add(hdct);

            initChosenHoaDonState();
            renderChosenHoaDon();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    public void addChosenKhachHang(KhachHang kh) {
        khachHangState = kh;

        lblKhachHangTen.setText(khachHangState.getTenKhachHang());
        lblKhachHangDienThoai.setText(khachHangState.getDienThoai());
        txtNguoiNhanTen.setText(khachHangState.getTenKhachHang());
        txtNguoiNhanSdt.setText(khachHangState.getDienThoai());

        new KhachHangService().updateKhachHang(khachHangState);

        chosenHoaDonState.setKhachHang(kh);
        HoaDonService.merge(chosenHoaDonState, userState);

        initChosenHoaDonState();
        renderChosenHoaDon();
        initHoaDonState();
        renderHoaDonTable();

    }

    private void filterSanPhamChiTiet() {
        try {
            List<SanPhamChiTiet> filterResults = SanPhamChiTietService.filterByKeyword(txtFilterTen.getText(), Integer.valueOf(txtFilterGiaMin.getText()), Integer.valueOf(txtFilterGiaMax.getText()));

            spctState = filterResults;
            renderSPCTTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTaoDonHang = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnRemoveFromCart = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFilterTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFilterGiaMin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFilterGiaMax = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnScanToCart = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblKhachHangDienThoai = new javax.swing.JLabel();
        lblKhachHangTen = new javax.swing.JLabel();
        btnFindKhachHang = new javax.swing.JButton();
        btnCreateKhachHang = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblHoaDonMa = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNguoiNhanSdt = new javax.swing.JTextField();
        txtNguoiNhanTen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblTienTong = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTienGiam = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtThanhToanTienMat = new javax.swing.JTextField();
        txtThanhToanTienChuyen = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        lblTienTong1 = new javax.swing.JLabel();
        lblTienTong2 = new javax.swing.JLabel();
        lblTienTong3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnCreateHoaDon = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(244, 244, 244));
        setForeground(new java.awt.Color(243, 243, 243));
        setMaximumSize(new java.awt.Dimension(1630, 800));
        setMinimumSize(new java.awt.Dimension(1630, 800));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1630, 800));
        setRequestFocusEnabled(false);

        lblTaoDonHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTaoDonHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaoDonHang.setText("BÁN HÀNG");

        jPanel5.setBackground(new java.awt.Color(243, 243, 243));

        panel8.setBackground(new java.awt.Color(243, 243, 243));
        panel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12)), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên sp", "Tên spct", "Thương hiệu", "Xuất xứ", "Chất liệu", "Đế", "Cổ", "Màu", "Size", "Số lượng mua", "Giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        btnRemoveFromCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnRemoveFromCart.setText("Xóa khỏi giỏ");
        btnRemoveFromCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1187, Short.MAX_VALUE)
            .addGroup(panel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRemoveFromCart))
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveFromCart)
                .addGap(31, 31, 31))
        );

        jPanel2.setBackground(new java.awt.Color(243, 243, 243));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setForeground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Từ khóa");

        jLabel2.setText("Giá");

        txtFilterGiaMin.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("-");

        txtFilterGiaMax.setText("999999999");
        txtFilterGiaMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilterGiaMaxActionPerformed(evt);
            }
        });

        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnFilter.setText("Tìm");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtFilterTen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtFilterGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFilterGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFilter)
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFilterTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtFilterGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtFilterGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setForeground(new java.awt.Color(204, 204, 204));

        btnScanToCart.setText("Quét mã");
        btnScanToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanToCartActionPerformed(evt);
            }
        });

        btnAddToCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add to basket.png"))); // NOI18N
        btnAddToCart.setText("Thêm vào giỏ hàng");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnScanToCart)
                .addGap(18, 18, 18)
                .addComponent(btnAddToCart)
                .addGap(19, 19, 19))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddToCart)
                    .addComponent(btnScanToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPCT", "Tên SP", "Tên SPCT", "Thương hiệu", "Xuất xứ", "Chất liệu", "Đế", "Cổ", "Màu", "Size", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblSanPhamChiTiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panel8, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(243, 243, 243));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel5.setText("SĐT");

        jLabel6.setText("Tên");

        lblKhachHangDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKhachHangDienThoai.setText("___________________");

        lblKhachHangTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKhachHangTen.setText("___________________");

        btnFindKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnFindKhachHang.setText("Tìm khách hàng");
        btnFindKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindKhachHangActionPerformed(evt);
            }
        });

        btnCreateKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnCreateKhachHang.setText("Thêm khách hàng");
        btnCreateKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblKhachHangTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateKhachHang))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lblKhachHangDienThoai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFindKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKhachHangDienThoai))
                    .addComponent(btnFindKhachHang, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKhachHangTen)
                    .addComponent(btnCreateKhachHang))
                .addGap(18, 18, 18))
        );

        jPanel9.setBackground(new java.awt.Color(243, 243, 243));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHoaDonMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDonMa.setText("XXXXXXXXXXXXXXXX");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Tên người nhận");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("SDT người nhận");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Tổng tiền");

        txtNguoiNhanSdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNguoiNhanSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNguoiNhanSdtActionPerformed(evt);
            }
        });

        txtNguoiNhanTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Mã hóa đơn");

        lblTienTong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienTong.setText("XXXXXXXXXXXXXXXX");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Số tiền giảm");

        lblTienGiam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienGiam.setText("XXXXXXXXXXXXXXXX");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("THÀNH TIỀN");

        lblThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThanhTien.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien.setText("XXXXXXXXXXXXXXXX");

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Tiền mặt");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Tiền chuyển");

        txtThanhToanTienMat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThanhToanTienMat.setText("0");

        txtThanhToanTienChuyen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThanhToanTienChuyen.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThanhToanTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhToanTienChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhToanTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhToanTienChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnThanhToan.setBackground(new java.awt.Color(255, 0, 0));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblTienTong1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienTong1.setText("VND");

        lblTienTong2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienTong2.setText("VND");

        lblTienTong3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTienTong3.setText("VND");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTienTong, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                .addComponent(lblTienGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTienTong2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addComponent(lblTienTong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblTienTong3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblHoaDonMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                        .addComponent(txtNguoiNhanTen, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNguoiNhanSdt, javax.swing.GroupLayout.Alignment.LEADING))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoaDonMa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguoiNhanTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguoiNhanSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienTong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienTong2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienTong1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienTong3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(243, 243, 243));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã", "Ngày tạo", "Giá trị", "Tên khách", "SĐT khách", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        btnCreateHoaDon.setBackground(new java.awt.Color(0, 204, 102));
        btnCreateHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCreateHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateHoaDon.setText("Tạo hóa đơn");
        btnCreateHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateHoaDon)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreateHoaDon)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTaoDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTaoDonHang)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void txtNguoiNhanSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNguoiNhanSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNguoiNhanSdtActionPerformed

    private void btnCreateHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateHoaDonActionPerformed
        createHoaDon();
    }//GEN-LAST:event_btnCreateHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        initChosenHoaDonState();
        renderChosenHoaDon();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        try {
            int num = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng mua"));
            if (num < 1) {
                throw new RuntimeException();
            }
            addSPCTToHDCT(num);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Phải nhập số từ 1 trở lên");
        }

    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnRemoveFromCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromCartActionPerformed
        removeSPCTFromGioHang();
    }//GEN-LAST:event_btnRemoveFromCartActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        completeHoaDon();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnScanToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanToCartActionPerformed
        if (tblHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Phai chon hoa don");
            return;
        }
        scanQRCode();

    }//GEN-LAST:event_btnScanToCartActionPerformed

    private void txtFilterGiaMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterGiaMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFilterGiaMaxActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        filterSanPhamChiTiet();
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnFindKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindKhachHangActionPerformed
        if (tblHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Phai chon hoa don truoc khi them khach hang");
            return;
        }

        new TimKiemKhachHangForm(this).setVisible(true);
    }//GEN-LAST:event_btnFindKhachHangActionPerformed

    private void btnCreateKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateKhachHangActionPerformed
        if (tblHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Phai chon hoa don truoc khi them khach hang");
            return;
        }
        KhachHangQuickCreateForm qcf = new KhachHangQuickCreateForm(this, userState);
        qcf.setVisible(true);
    }//GEN-LAST:event_btnCreateKhachHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnCreateHoaDon;
    private javax.swing.JButton btnCreateKhachHang;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnFindKhachHang;
    private javax.swing.JButton btnRemoveFromCart;
    private javax.swing.JButton btnScanToCart;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblHoaDonMa;
    private javax.swing.JLabel lblKhachHangDienThoai;
    private javax.swing.JLabel lblKhachHangTen;
    private javax.swing.JLabel lblTaoDonHang;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTienGiam;
    private javax.swing.JLabel lblTienTong;
    private javax.swing.JLabel lblTienTong1;
    private javax.swing.JLabel lblTienTong2;
    private javax.swing.JLabel lblTienTong3;
    private javax.swing.JPanel panel8;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTextField txtFilterGiaMax;
    private javax.swing.JTextField txtFilterGiaMin;
    private javax.swing.JTextField txtFilterTen;
    private javax.swing.JTextField txtNguoiNhanSdt;
    private javax.swing.JTextField txtNguoiNhanTen;
    private javax.swing.JTextField txtThanhToanTienChuyen;
    private javax.swing.JTextField txtThanhToanTienMat;
    // End of variables declaration//GEN-END:variables
}
