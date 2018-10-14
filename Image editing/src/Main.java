
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author berna
 */
import java.awt.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

public class Main {

    private JFrame ventana;
    private File file;
    private draw dr;

    public Main() {

        this.file = new File("loros.jpg");
        

        //window setting
        this.ventana = new JFrame("EDDIE THOR DE Y MARGENES");
        this.ventana.setSize(500, 500);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setResizable(false);
   

        //container
        Container cp = ventana.getContentPane();

        cp.setLayout(new GridBagLayout());
        cp.add(addAll());
        this.ventana.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }

    public Component addAll() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints f = new GridBagConstraints();
        f.gridx = 0;
        f.gridy = 0;
        f.gridheight = 4;
        this.dr = new draw(file);
        dr.setSize(250, 250);
        panel.add(dr, f);

        f.insets = new Insets(10, 10, 10, 10);
        f.gridheight = 1;
        f.gridx = 1;
        f.gridy = 0;
        Xbutton aB = new Xbutton(dr.getJmg());
        aB.setAcction(1);
        aB.setMain(this);
        aB.setText("Subir brillo");
        aB.setBackground(Color.white);
        panel.add(aB, f);

        f.gridy = 1;
        Xbutton sB = new Xbutton(dr.getJmg());
        sB.setAcction(2);
        sB.setMain(this);
        sB.setText("Bajar brillo");
        Font fuente = new Font("hola", 0, 0);
        //fuente.
        sB.setForeground(Color.white);
        sB.setBackground(Color.black);
        panel.add(sB, f);

        f.gridy = 2;
        Xbutton Rs = new Xbutton(dr.getJmg());
        Rs.setAcction(3);
        Rs.setMain(this);
        Rs.setText("Reset");
        panel.add(Rs, f);

        f.gridy = 3;
        Xbutton Gr = new Xbutton(dr.getJmg());
        Gr.setAcction(4);
        Gr.setMain(this);
        Gr.setBackground(Color.LIGHT_GRAY);
        Gr.setText("Escala de grises");
        panel.add(Gr, f);

        f.gridy = 4;
        Xbutton ChB = new Xbutton(dr.getJmg());
        Xbutton.color a = ChB.new color(0);
        ChB.addActionListener(a);
        ChB.setMain(this);
        ChB.setBackground(Color.blue);
        ChB.setText("Subir Azul");
        panel.add(ChB, f);

        f.gridy = 5;
        Xbutton ChG = new Xbutton(dr.getJmg());
        Xbutton.color b = ChG.new color(1);
        ChG.addActionListener(b);
        ChG.setMain(this);
        ChG.setBackground(Color.green);
        ChG.setText("Subir Verde");
        panel.add(ChG, f);

        f.gridy = 6;
        Xbutton ChR = new Xbutton(dr.getJmg());
        Xbutton.color C = ChR.new color(2);
        ChR.addActionListener(C);
        ChR.setBackground(Color.red);
        ChR.setMain(this);
        ChR.setText("Subir Rojo");
        panel.add(ChR, f);
        
        f.gridx=0;
        f.gridy=5;
        JLabel logo = new JLabel("ED Y TOR D HIMAJENES");
        logo.setFont(new Font("asd", 0, 28));
        panel.add(logo,f);

        return panel;
    }

    public JFrame getVentana() {
        return ventana;
    }

    public draw getDraw() {
        return dr;
    }

}
