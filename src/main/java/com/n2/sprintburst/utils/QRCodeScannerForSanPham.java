/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.utils;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.n2.sprintburst.service.SanPhamChiTietService;
import com.n2.sprintburst.view.sanPham.SanPhamView;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JFrame;

/**
 *
 * @author Mtt
 */
public class QRCodeScannerForSanPham extends JFrame implements Runnable, ThreadFactory {
    
    private static final long serialVersionUID = 6441489157408381878L;
    
    private Executor executor = Executors.newSingleThreadExecutor(this);
    
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    
    private SanPhamView parent;
    
    public QRCodeScannerForSanPham(SanPhamView parent) {
        super();
        
        this.parent = parent;
        
        setLayout(new FlowLayout());
        setTitle("Scan QR");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Dimension size = WebcamResolution.QVGA.getSize();
        
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);
        
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        
        add(panel);
        
        pack();
        setVisible(true);
        
        executor.execute(this);
    }
    
    @Override
    public void run() {
        
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            Result result = null;
            BufferedImage image = null;
            
            if (webcam.isOpen()) {
                
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
                
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                
                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }
            if (result != null) {
                System.err.println("PARSED: " + result.getText());
                parent.displaySPCTUpdateView(SanPhamChiTietService.findById(Integer.parseInt(result.getText())));
                webcam.close();
                this.dispose();
                return;
                
            }
            
        } while (true);
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }
    
    public static void main(String[] args) {
        new QRCodeScannerForBanHang(null);
    }
}
