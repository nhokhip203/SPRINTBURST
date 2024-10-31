/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mtt
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;

public class PrintReceipt implements Printable {

    // Attributes..
    private PrinterJob printerJob;
    private PageFormat pageFormat;
    private Paper paper;

    private final int MARGIN = 1;

    public PrintReceipt() {

        printerJob = PrinterJob.getPrinterJob();
        pageFormat = printerJob.defaultPage(); // Getting the page format.

        paper = new Paper(); // Create a new paper..

        // If you are working on printer rather than Thermal printers
        // then change the width and height accordingly.
        // I set them to 1000 value because that was for 
        // receipt which will not be larger than 1000 points
        // actually this height does not mean the height of 
        // paper get out from the printer, this is the height
        // of the printable area which you can use.
        int width = 216;
        int height = 1000;

        // width = totalWidthOfPage - (MARGIN * 2);
        // height = numberOfLines * 10 - (MARGIN * 2);
        paper.setImageableArea(MARGIN, MARGIN, width, height);
        pageFormat.setPaper(paper);

        pageFormat.setOrientation(PageFormat.PORTRAIT);
        printerJob.setPrintable(this, pageFormat);

        try {

            printerJob.print();

        } catch (PrinterException ex) {

            JOptionPane.showMessageDialog(null, "Printing Failed, Error: " + ex.toString());

        }

    }

    /**
     * Printing with Graphics..
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.black);

        g2d.setFont(new Font("Consolas", Font.BOLD, 12));
        g2d.drawString(spaces(10) + "CITY AUTO", 4, 10);
        g2d.setFont(new Font("Consolas", Font.PLAIN, 10));
        g2d.drawString(spaces(4) + "Address..", 0, 22);

        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        return PAGE_EXISTS;

    }

    /**
     * Adding spaces into the num.
     *
     * @param total spaces
     * @return all spaces in string.
     */
    public String spaces(int num) {

        String sp = "";
        for (int i = 0; i < num; i++) {
            sp += " ";
        }

        return sp;

    }

}
