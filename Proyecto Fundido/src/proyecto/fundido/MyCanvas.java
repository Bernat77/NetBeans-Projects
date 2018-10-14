/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.fundido;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author berna
 */
public class MyCanvas extends Canvas {

    private Graphics dbg;
    private Image img;
    private BufferedImage img1;
    private BufferedImage img2;
    private byte[] raster;
    private byte[] raster2;
    private float[] dif;
    private byte[][] frames;
    private Main main;
    private BufferedImage[] hola;

    public MyCanvas(File file1, File file2) throws IOException {

        img1 = ImageIO.read(file1);
        img2 = ImageIO.read(file2);
        calculateDif(100);
        fillFrames();

    }

    @Override
    public void paint(Graphics g) {
        img = createImage(getWidth(), getHeight());
        dbg = img.getGraphics();
        paintComponent(dbg);
        g.drawImage(img, 0, 0, this);

    }

    public void paintComponent(Graphics g) {
        g.drawImage(img1, 0, 0, null);

    }

    public void calculateDif(int nframes) throws IOException {

        raster = ((DataBufferByte) img1.getRaster().getDataBuffer()).getData();
        raster2 = ((DataBufferByte) img2.getRaster().getDataBuffer()).getData();

        this.dif = new float[raster.length];

        for (int i = 0; i < dif.length; i++) {
            dif[i] = Byte.toUnsignedInt(raster2[i]) - Byte.toUnsignedInt(raster[i]);
        }

        frames = new byte[nframes][raster.length];

        for (int i = 0; i < frames.length; i++) {
            for (int j = 0; j < frames[i].length; j++) {
                frames[i][j] = raster[j];
            }
        }
    }

    public void animateFade() throws InterruptedException {

        boolean patata = true;
        int secs = 100;
        while (patata) {

            for (int i = 0; i < frames.length; i++) {
                for (int j = 0; j < frames[i].length; j++) {
                    raster[j] = frames[i][j];
                }
                this.repaint();
                TimeUnit.MILLISECONDS.sleep(secs);

            }
            for (int j = 0; j < raster.length; j++) {
                raster[j] = raster2[j];
            }
            this.repaint();
            TimeUnit.MILLISECONDS.sleep(1000);

            for (int i = frames.length - 1; i > 0; i--) {
                for (int j = 0; j < frames[i].length; j++) {
                    raster[j] = frames[i][j];
                }
                this.repaint();
                TimeUnit.MILLISECONDS.sleep(secs);

            }
            for (int j = 0; j < raster.length; j++) {
                raster[j] = frames[0][j];
            }
            this.repaint();
            TimeUnit.MILLISECONDS.sleep(1000);
        }

    }

    public void fillFrames() {

        for (int i = 0; i < frames.length; i++) {
            for (int j = 0; j < frames[i].length; j++) {
                float res = (i) * (dif[j] / (frames.length));
                frames[i][j] +=(byte) res;
            }

        }
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
