package com.n2.sprintburst.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.n2.sprintburst.entity.HoaDon;
import com.n2.sprintburst.entity.HoaDonChiTiet;
import com.n2.sprintburst.entity.LichSuHoaDon;
import com.n2.sprintburst.service.HoaDonChiTietService;
import com.n2.sprintburst.service.HoaDonService;
import com.n2.sprintburst.service.LichSuHoaDonService;
import com.n2.sprintburst.utils.ExportPdfHoaDon;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HoaDonView extends javax.swing.JInternalFrame {

    /**
     * Creates new form Test
     */
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    DefaultTableModel modelHoaDonChiTiet = new DefaultTableModel();
    DefaultTableModel modelLichSuHoaDon = new DefaultTableModel();
    HoaDonService hoaDonService = new HoaDonService();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    LichSuHoaDonService lichSuHoaDonService = new LichSuHoaDonService();

    List<HoaDon> hd = new ArrayList<>();
    List<HoaDonChiTiet> hdct = new ArrayList<>();
    List<LichSuHoaDon> lshd = new ArrayList<>();

    public HoaDonView() {
        FlatLightLaf.setup();
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        defaultTableModel = (DefaultTableModel) tblHoaDon.getModel();
        modelHoaDonChiTiet = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        modelLichSuHoaDon = (DefaultTableModel) tblLichSuHoaDon.getModel();
        fillData(0);
        setUpTableHoaDon();
        setUpTableChiTiet();
        setUpTableLichSu();
    }

    public void setUpTableHoaDon() {
        tblHoaDon.setModel(defaultTableModel);
        defaultTableModel.setColumnIdentifiers(new String[]{
            "STT", "Mã hóa đơn", "Khách hàng", "Nhân viên", "Ngày tạo", "Tổng trước giảm giá", "Tổng sau giảm giá", "Tên người nhận", "Số điện thoại",
            "Trạng thái"});
    }

    public void setUpTableChiTiet() {
        tblHoaDonChiTiet.setModel(modelHoaDonChiTiet);
        modelHoaDonChiTiet.setColumnIdentifiers(new String[]{
            "STT", "Mã hóa đơn", "Sản phẩm chi tiết", "Số lượng", "Gía bán", "Trạng thái"});
    }

    public void setUpTableLichSu() {
        tblLichSuHoaDon.setModel(modelLichSuHoaDon);
        modelLichSuHoaDon.setColumnIdentifiers(new String[]{
            "STT", "Mã hóa đơn", "Nhân viên", "Ghi chú", "Ngày tác động"});
    }

    public void fillData(int index) {
        hd = hoaDonService.getAllHoaDon();
        defaultTableModel.setRowCount(0);
        int soThuTu = 1;
        for (HoaDon hoaDon : hd) {
            if (index == 0) {

                defaultTableModel.addRow(new Object[]{
                    soThuTu++,
                    hoaDon.getMaHoaDon(),
                    hoaDon.getKhachHang() == null ? null : hoaDon.getKhachHang().getMaKhachHang(),
                    hoaDon.getNhanVien(),
                    hoaDon.getNgayTao(),
                    hoaDon.getTongTruocGiamGia(),
                    hoaDon.getTongSauGiamGia(),
                    hoaDon.getTenNguoiNhan(),
                    hoaDon.getDienThoaiNguoiNhan(),
                    hoaDon.getTrangThaiHoaDon() == null ? null : hoaDon.getTrangThaiHoaDon().getTen(),
                    hoaDon.getPhieuGiamGia() == null ? null : hoaDon.getPhieuGiamGia().getTenGiamGia()
                });
            } else if (index == hoaDon.getTrangThaiHoaDon().getId()) {
                defaultTableModel.addRow(new Object[]{
                    soThuTu++,
                    hoaDon.getMaHoaDon(),
                    hoaDon.getKhachHang() == null ? null : hoaDon.getKhachHang().getId(),
                    hoaDon.getNhanVien(),
                    hoaDon.getNgayTao(),
                    hoaDon.getTongTruocGiamGia(),
                    hoaDon.getTongSauGiamGia(),
                    hoaDon.getTenNguoiNhan(),
                    hoaDon.getDienThoaiNguoiNhan(),
                    hoaDon.getTrangThaiHoaDon() == null ? null : hoaDon.getTrangThaiHoaDon().getTen(),
                    hoaDon.getPhieuGiamGia() == null ? null : hoaDon.getPhieuGiamGia().getTenGiamGia()
                });
            }

        }
    }

    public void loadTableHoaDonChiTiet() {
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        modelHoaDonChiTiet.setRowCount(0);
        int hoaDonId = Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
        String maH = tblHoaDon.getValueAt(row, 1).toString();
        List<HoaDonChiTiet> hdctList = hoaDonChiTietService.getHoaDonByMa(maH);
        int soThuTu = 1;  // Biến đếm số thứ tự
        for (HoaDonChiTiet hd : hdctList) {
            modelHoaDonChiTiet.addRow(new Object[]{
                soThuTu++, // Thêm số thứ tự
                hd.getHoaDon().getMaHoaDon(),
                hd.getSanPhamChiTiet().getTenSanPhamChiTiet(),
                hd.getSoLuong(),
                hd.getGiaBan(),
                hd.isTrangThai() ? "Còn hàng" : "Hết hàng",});
        }
    }

    public void loadTableLichSuHoaDon() {
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }

        modelLichSuHoaDon.setRowCount(0);
        int hoaDonId = Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
        String ma = tblHoaDon.getValueAt(row, 1).toString();
        List<LichSuHoaDon> lshdList = lichSuHoaDonService.getLichSuHoaDonBtMa(ma);
        System.out.println("sixe" + lshdList.size());
        int soThuTu = 1;  // Biến đếm số thứ tự
        for (LichSuHoaDon hd : lshdList) {
            modelLichSuHoaDon.addRow(new Object[]{
                soThuTu++, // Thêm số thứ tự
                hd.getHoaDon().getMaHoaDon(),
                hd.getNhanVien().getId(),
                hd.getGhiChu(),
                hd.getNgayTacDong(),});
        }
    }

    private void exportExcelHD() {
        try {
            JFileChooser fileChooser = new JFileChooser("/");
            fileChooser.setDialogTitle("Export xls file");
            FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Excel spreadsheet files", "xls", "xlsx", "xism");
            fileChooser.setFileFilter(extFilter);

            int confirm = fileChooser.showSaveDialog(this);

            if (confirm == JFileChooser.APPROVE_OPTION) {
                // Create Excel workbook and sheet
                XSSFWorkbook excelWorkbook = new XSSFWorkbook();
                XSSFSheet sheet = excelWorkbook.createSheet("HD_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_kk_mm_ss")));

                XSSFRow row = null;
                Cell cell = null;

                // Header row
                row = sheet.createRow(0); // Adjust row index here if needed
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("DANH SACH HOA DON");
                row = sheet.createRow(1); // Adjust header row
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Ma Hoa Don");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Khach Hang");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Nhan Vien");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Ngay Tao");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Tong Truoc Giam Gia");
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Tong Sau Giam Gia");
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Ghi Chu");
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue("Ten Nguoi Nhan");
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue("So Dien Thoai");
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue("Dia Chi");
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue("Trang Thai");
                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue("Phieu Giam Gia");
                // Fill sheet with table data from tblHoaDon
                for (int rowIndex = 0; rowIndex < tblHoaDon.getRowCount(); rowIndex++) {
                    XSSFRow sheetRow = sheet.createRow(rowIndex + 2); // Start after header

                    for (int colIndex = 0; colIndex < tblHoaDon.getColumnCount(); colIndex++) {
                        XSSFCell cellData = sheetRow.createCell(colIndex);

                        Object data = tblHoaDon.getValueAt(rowIndex, colIndex);

                        if (data == null) {
                            continue;
                        }

                        cellData.setCellValue(data.toString());
                    }
                }

                // Ensure the chosen file has the correct extension
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                FileOutputStream fos = new FileOutputStream(filePath);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                excelWorkbook.write(bos);
                bos.close();
                excelWorkbook.close(); // Close the workbook to release resources

                JOptionPane.showMessageDialog(this, "Xuất ra Ex thành công!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during export: " + e.getMessage());
        }
    }
//ok

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbbTrangThaiTimKiem = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSuHoaDon = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        btnXuatEx = new javax.swing.JButton();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1630, 800));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setText("Tìm kiếm hóa đơn :");

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblHoaDon.setShowGrid(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel1.setText("Trạng thái hóa đơn");

        cbbTrangThaiTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã thanh toán", "Đã hủy" }));
        cbbTrangThaiTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1620, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTrangThaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbTrangThaiTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch sử hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblLichSuHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblLichSuHoaDon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save as.png"))); // NOI18N
        jButton4.setText("In hóa đơn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnXuatEx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Tick.png"))); // NOI18N
        btnXuatEx.setText("Xuất danh sách");
        btnXuatEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(174, 174, 174)
                .addComponent(btnXuatEx)
                .addGap(589, 589, 589))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatEx)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(dtm);
        tblHoaDon.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXuatExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExActionPerformed
        // TODO add your handling code here:
        exportExcelHD();

    }//GEN-LAST:event_btnXuatExActionPerformed

    private void cbbTrangThaiTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiTimKiemActionPerformed
        // TODO add your handling code here:
// Tạo TableRowSorter để có thể áp dụng bộ lọc trên bảng
// Lấy giá trị trạng thái được chọn trong ComboBox
        int selectedStatus = cbbTrangThaiTimKiem.getSelectedIndex();

// Kiểm tra nếu người dùng chọn "Tất cả", hiển thị toàn bộ dữ liệu
        if (selectedStatus == 0) {
            // Show all records
            fillData(0);
        } else if (selectedStatus == 1) {
            // Apply filter for "Chờ thanh toán" (Unpaid)
            System.out.println("Hiển thị hóa đơn chờ thanh toán");
            fillData(1);
        } else if (selectedStatus == 2) {
            // Apply filter for "Chưa thanh toán" (Pending Payment)
            System.out.println("Hiển thị hóa đơn chưa thanh toán");
            fillData(2);
        } else if (selectedStatus == 3) {
            // Apply filter for "Đã hủy" (Pending Payment)
            System.out.println("Hiển thị hóa đơn đã hủy");
            fillData(3);
        } else {
            System.out.println("Trạng thái không hợp lệ");
        }
    }//GEN-LAST:event_cbbTrangThaiTimKiemActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        loadTableHoaDonChiTiet();
        loadTableLichSuHoaDon();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            int row = this.tblHoaDon.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn 1 hóa đơn để in");
                return;
            }
            String ma = this.tblHoaDon.getValueAt(row, 1).toString();
            HoaDon hoaDon = hoaDonService.findHdByMa(ma);
            if (hoaDon.getId() == 2) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    JFileChooser avatarChooser = new JFileChooser("D:\\");
                    avatarChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //Giới hạn chỉ chọn đc thư mục
                    FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
                    avatarChooser.setFileFilter(avatarFilter);
                    avatarChooser.setAcceptAllFileFilterUsed(false);
                    int selectFileCheck = avatarChooser.showOpenDialog(this);
                    File selectedFile = avatarChooser.getSelectedFile();
                    if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                        return;
                    }
                    //Muốn lấy đường dẫn và để vào export PDF thì 
                    String path = selectedFile.getAbsolutePath();
                    if (hoaDon.getId() == 0) {
                        ExportPdfHoaDon export = new ExportPdfHoaDon();
                        export.exportBill(hoaDon, hdct, path);
                    }
                    JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hãy chọn 1 hóa đơn đã thanh toán");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXuatEx;
    private javax.swing.JComboBox<String> cbbTrangThaiTimKiem;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblLichSuHoaDon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
