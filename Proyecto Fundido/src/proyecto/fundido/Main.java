/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.fundido;

import java.awt.Container;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.imageio.*;
import java.awt.image.Raster;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author bernat
 */
public class Main {

    private JFrame window;
    private File imagen1;
    private File imagen2;
    private byte[][] frames;
    private MyCanvas canvas;

    public Main() throws IOException {
        
        imagen1 = new File("1.jpg");
        imagen2 = new File("2.jpg");
        
        this.window = new JFrame("Fade In");
        window.setSize(400, 400);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container CP = window.getContentPane();
        CP.setLayout(new GridBagLayout());
        GridBagConstraints f = new GridBagConstraints();
        f.gridy=0;
        f.gridx=0;
        f.gridheight=0;
        f.gridwidth=0;               
        
        this.canvas = new MyCanvas(imagen1, imagen2);
        canvas.setSize(300, 300);
        CP.add(canvas,f);

    }

    public static void main(String[] args) throws InterruptedException {
        try {
            Main hola = new Main();
            hola.canvas.animateFade();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
