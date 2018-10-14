/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class draw extends Canvas {

    private Jmage jmg;
    private BufferedImage bf;
    private Main Main;

    public draw(File f) {

        this.jmg = new Jmage(f);
        //loadImage(f);

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(jmg.getImg(), 0, 0, null);

    }

    public void loadImage(File f) {

        try {
            this.bf = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
        }
    }

    public Jmage getJmg() {
        return this.jmg;
    }

}
