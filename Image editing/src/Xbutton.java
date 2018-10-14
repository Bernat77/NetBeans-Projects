/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author berna
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Xbutton extends JButton {

    private Main main;
    private Jmage img;

    public Xbutton(Jmage img) {
        this.img = img;
    }

    public class addBright implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            img.changeBrightness(10);
            main.getDraw().repaint();
        }

    }

    public class subsBright implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            img.changeBrightness(-10);
            main.getDraw().repaint();
        }

    }

    public class resetImg implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            img.restartRaster();
            main.getDraw().repaint();
        }

    }

    public class turnGrey implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            img.convertToGreyScale();
            main.getDraw().repaint();

        }

    }

    public class color implements ActionListener {

        private int color;

        public color(int n) {
            this.color = n;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            img.changeColor(this.color, 10);
            main.getDraw().repaint();
        }
    }

    public void setAcction(int n) {
        switch (n) {
            case 1:
                addActionListener(new Xbutton.addBright());
                break;
            case 2:
                addActionListener(new Xbutton.subsBright());
                break;
            case 3:
                addActionListener(new Xbutton.resetImg());
                break;
            case 4:
                addActionListener(new Xbutton.turnGrey());
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
