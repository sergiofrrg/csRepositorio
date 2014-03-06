/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;
/**
 * Write a description of class Lienzo here.
 * 
 * @author Francisco Dominguez
 * @date 14/03/2013
 * @version 20130314
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Lienzo extends JPanel {
    /**
     * Constructor for objects of class Lienzo
     */
    
    java.util.List<Token> col=new java.util.ArrayList<Token>();
    Interprete inter;
    
    public Lienzo()
    {
        super();
        setPreferredSize(new Dimension(320,240));
        this.col = new ArrayList<Token>();
        
    }
        
    public void addI(java.util.List<Token> l){
        col=l;
        repaint();
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.red);
        Tortuga t = new Tortuga (g);
        this.inter = new Interprete(t);
        if (!this.col.isEmpty())
            inter.analiza(col);
//        t.pen(false);
//        t.turn(90);
//        t.move(240/2);
//        t.turn(-90);
//        t.move(320/2);
//        t.pen(true);
//        t.move(50);
//        t.turn(90);
//        t.move(50);
//        g.setColor(Color.black);
//        t.turn(-120);
//        t.move(50);
//        t.turn(90);
//        t.move(50);
            //System.out.println(al.analizar("15 156;").toString()); 
    }
    public void redraw(){
        this.repaint();
    }
}