/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.sprintburst.view;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Progressbar {

    public static void main(String[] args) {
        ProgressbarFrame pf = new ProgressbarFrame();
        LoginFrame lg = new LoginFrame();
        pf.setVisible(true);

        for (int i = 1; i <= 100; ++i) {
            try {
                Thread.sleep(40);

                pf.progressBar.setValue(i);

                if (i % 2 == 0) {
                    pf.pleaseWait.setText("Pleasw Wait..");
                } else {
                    pf.pleaseWait.setText("Pleasw Wait...");
                }
                if(i==100){
                    pf.setVisible(false);
                    lg.setVisible(true);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Progressbar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
