/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.image.Raster;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Jmage {

    private draw canvas;   // canvas where the object belongs to
    private BufferedImage img;
    private byte[] oras;  // first data bank byte type which contains all pixel information
    private int[] intbA;
    private byte[] mras; //modified raster
    private Raster ras; // a variable raster type where we'll store the reference to the current raster at any time.

    public Jmage(File f) {

        loadImage(f);
        ras = this.img.getRaster();
        mras = rasterToByteArray();
        copyRaster();
        drawRectangle(100, 100, 150, 150);

    }

    public void copyRaster() {
        oras = new byte[mras.length];
        intbA = new int[mras.length];
        for (int i = 0; i < mras.length; i++) {
            oras[i] = mras[i];
            intbA[i] = Byte.toUnsignedInt(mras[i]);
        }
    }

    public void convertToGreyScale() {

        Integer a;

        for (int i = 0; i < mras.length; i += 3) {
            a = ((Byte.toUnsignedInt(mras[i]) + Byte.toUnsignedInt(mras[i + 1]) + Byte.toUnsignedInt(mras[i + 2])) / 3);

            mras[i] = a.byteValue();
            mras[i + 1] = a.byteValue();
            mras[i + 2] = a.byteValue();
        }

    }

    public void changeBrightness(int bright) {

        for (int i = 0; i < mras.length; i++) {

            Integer x = Byte.toUnsignedInt(mras[i]) + bright;
            intbA[i] += bright;

            if (x > 255) {
                x = 255;
            } else if (x < 0) {
                x = 0;
            }

            mras[i] = x.byteValue();
        }

    }

    public void changeColor(int pos, int range) {

        for (int i = pos; i < mras.length; i += 3) {

            Integer x = Byte.toUnsignedInt(mras[i]) + range;
            if (x > 255) {
                x = 255;
            } else if (x < 0) {
                x = 0;
            }
            mras[i] = x.byteValue();

        }
    }

    public void drawRectangle(int height, int width, int x, int y) {
        

        for (int i = (this.img.getWidth() * 3 * x); i < this.img.getWidth() * 3 * (x + height); i += this.img.getWidth() * 3) {
            for (int j = (i) + (y * 3); j < (i) + ((y + width) * 3); j+=3) {

                mras[j] = (byte) 200;
                mras[j+1] = (byte) 0;
                mras[j+2] = (byte) 255;
            }

        }
    }

    public void loadImage(File f) {

        try {
            this.img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("Imagen no encontrada");
        }
    }

    public byte[] rasterToByteArray() {

        if (ras.getDataBuffer().getDataType() != DataBuffer.TYPE_BYTE) {
            throw new IllegalArgumentException("not a RGB byte data type");
        }

        System.out.println("Compatible image type");
        return ((DataBufferByte) ras.getDataBuffer()).getData();
    }

    public void restartRaster() {

        for (int i = 0; i < mras.length; i++) {
            mras[i] = oras[i];
            intbA[i] = (int) Byte.toUnsignedInt(oras[i]);
        }

    }

    public draw getCanvas() {
        return canvas;
    }

    public void setCanvas(draw canvas) {
        this.canvas = canvas;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

}
