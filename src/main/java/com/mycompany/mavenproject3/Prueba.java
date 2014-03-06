/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

/**
 *
 * @author Aza
 */
public class Prueba {
    public static void main (String[] args){
        AnalizadorLexico al = new AnalizadorLexico();
        System.out.println(al.analizar("si (5>3) entonces avanza 50;").toString());
    }
}
