/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.utils;

import com.n2.sprintburst.entity.HoaDon;
import com.n2.sprintburst.entity.HoaDonChiTiet;
import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.BaseFont;

/**
 *
 * @author NhokHip
 */
public class ExportPdfHoaDon {
        public static final String pathUnicode = "font\\unicode.ttf";

    public void exportBill(HoaDon hoaDon, List<HoaDonChiTiet> listHoaDonChiTiet, String pathFile) {
        try {
            String path = pathFile + "\\" + "hoa_don" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            File file = new File(path);
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(pathUnicode, BaseFont.IDENTITY_H);

            Table table = new Table(columWidth);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new Cell().add("Bill Sneaker Store").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("Mã hóa đơn: " + hoaDon.getMaHoaDon()+ "\n Sneaker Store").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 250, 200, 200};
            Table customerInforTable = new Table(colWidth);
            customerInforTable.setFont(font);
            customerInforTable.addCell(new Cell(0, 4)
                    .add("Thông tin khách hàng").setBold().setBorder(Border.NO_BORDER));

            if (hoaDon.getKhachHang() == null) {
                customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Khách bán lẻ").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("No").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("No").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Ngày thanh toán:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
                String date = sdf.format(hoaDon.getNgayTao());
                customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            } else {
                customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getTenKhachHang()).setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getDienThoai()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getDiaChi()).setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Ngày thanh toán:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa dd-MM-yyyy");
                String date = sdf.format(hoaDon.getNgayTao());
                customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
             
            }

            float itemColWidth[] = {15, 110, 170, 50, 110, 110};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thông tin SP").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("SL").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giá bán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thành tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            int index = 1;
            
            if (!hoaDon.getKhachHang().getMaKhachHang().equals("KH000")) {
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("% giảm giá").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                
            }
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tổng tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
           
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền thừa").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
          
            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4)
                    .add("Lưu ý: Quý khách hãy giữ lại hóa đơn,\nNếu sản phẩm gặp vấn đề gì có thể trả hàng trong vòng 3 ngày,\n chỉ thực hiện trả hàng cho những sản phẩm không áp dụng khuyến mại.\nNhững sản phẩm được đánh dấu (*) ở giá bán là những sản phẩm đã có giảm giá khuyến mại").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4)
                    .add("Trường cao đẳng FPT Polytechnich, P.Trịnh Văn Bô,\nP.Phương Canh, Q.Nam Từ Liêm, TP.Hà Nội").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0686868686").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTable);
            document.add(new Paragraph("\n"));
            document.add(itemTable);
            if (!hoaDon.getKhachHang().getMaKhachHang().equals("KH000")) {
                document.add(customerLuuY);
            }
            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);

            document.close();

            if (!Desktop.isDesktopSupported()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

}
