package com.mycompany.mavenproject3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame implements ActionListener{
private Lienzo lienzo;
private JButton b1;
JTextArea textArea = new JTextArea(20, 20);
    /**
     * Constructor for objects of class Ventana
     */
    public Ventana()
    {
        super();
        JPanel barraHerramientas = new JPanel();
        //JTextArea textArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        JLabel labelError=new JLabel("Consola:");
        //barraHerramientas.setLayout(new FlowLayout());
        b1 = new JButton("Ejecuta");
        b1.setActionCommand("s");
        b1.addActionListener(this);
        barraHerramientas.add(b1);
        
        lienzo=new Lienzo();
        //getContentPane().setLayout(new BorderLayout()); // No hace falta, por defecto ya es BorderLayout
        getContentPane().add(barraHerramientas, BorderLayout.NORTH);
        getContentPane().add(lienzo, BorderLayout.CENTER);
        getContentPane().add(textArea,BorderLayout.WEST);
        getContentPane().add(labelError,BorderLayout.SOUTH);

        pack();
    }
    public static void main(String[] args){
        Ventana v=new Ventana();
        v.setVisible(true);
    }
    /*********************************
     * ActionListener implementation
     *********************************/
@Override
    public void actionPerformed(ActionEvent e) {
        if ("s".equals(e.getActionCommand())) {
            //setCurrentTool(sT);
             AnalizadorLexico aLex = new AnalizadorLexico();
             String texto = textArea.getText();
             String [] lineas = texto.split("\n");
             texto = "";
             for(int i=0;i<lineas.length;i++){
                 texto=texto+lineas[i]+"#";
             }
             System.out.print(texto);
             System.out.print(aLex.analizar(texto));
             lienzo.addI(aLex.analizar(texto));
        }
    } 
}
