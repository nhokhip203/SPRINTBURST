/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.n2.sprintburst.view.banHang;

import com.n2.sprintburst.view.*;
import com.n2.sprintburst.entity.HoaDon;
import com.n2.sprintburst.entity.KhachHang;
import com.n2.sprintburst.entity.NhanVien;
import com.n2.sprintburst.service.HoaDonService;
import com.n2.sprintburst.service.KhachHangService;
import com.n2.sprintburst.service.NhanVienService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class TimKiemKhachHangForm extends javax.swing.JFrame {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    DefaultTableModel defaultTableModel1 = new DefaultTableModel();
    DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
    KhachHangService khachHangService = new KhachHangService();
    HoaDonService hoaDonService = new HoaDonService();
    NhanVienService nhanVienService = new NhanVienService();
    List<KhachHang> kh = new ArrayList<>();
    List<NhanVien> nhanVienLst = new ArrayList<>();

    List<HoaDon> hd = new ArrayList<>();

    static int idChon;

    BanHangForm parent;

    /**
     * Creates new form NhanVienView
     */
    public TimKiemKhachHangForm(BanHangForm parent) {
        initComponents();
        setFrameIcon();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setLocationRelativeTo(null);

        this.parent = parent;

        setUpTable();
        defaultTableModel = (DefaultTableModel) tblBang.getModel();

        fillData();

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

    public void setUpTable() {
        tblBang.setModel(defaultTableModel);
        defaultTableModel.setColumnIdentifiers(new String[]{"Stt", "Mã khách hàng", "Tên khách hàng", "Emai", "Điện thoại", "Điạ chỉ", "Ghi chú", "Ngày tạo", "Tên nhân viên"});
    }

    public void fillData() {
        kh = khachHangService.getAllKhachHang();
//        cboIdNhanVien.removeAllItems();
        defaultTableModel.setRowCount(0);

        for (KhachHang khachHang : kh) {
            defaultTableModel.addRow(new Object[]{
                khachHang.getId(),
                khachHang.getMaKhachHang(),
                khachHang.getTenKhachHang(),
                khachHang.getEmail(),
                khachHang.getDienThoai(),
                khachHang.getDiaChi(),
                khachHang.getGhiChu(),
                khachHang.getNgayTao(),
                khachHang.getNhanVien().getHoTen(),});
        }
        nhanVienLst = nhanVienService.getAllNhanVien();
    }

    int index;

    private void chooseKhachHang() {
        try {
            int idx = tblBang.getSelectedRow();

            if (idx == -1) {
                return;
            }

            KhachHang chosen = new KhachHangService().getKhachHangById(String.valueOf(tblBang.getValueAt(idx, 0)));
            parent.addChosenKhachHang(chosen);
            this.dispose();
        } catch (Exception e) {
            throw e;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnChooseKhachHang = new javax.swing.JButton();

        setTitle("Tìm kiếm khách hàng - SPRINT BURST");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblBang);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnChooseKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnChooseKhachHang.setText("Chọn khách hàng");
        btnChooseKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChooseKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChooseKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) tblBang.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(dtm);
        tblBang.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnChooseKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseKhachHangActionPerformed
        chooseKhachHang();
    }//GEN-LAST:event_btnChooseKhachHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseKhachHang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
