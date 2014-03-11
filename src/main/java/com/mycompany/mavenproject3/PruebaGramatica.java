/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

/**
 *
 * @author Aza
 */
public class PruebaGramatica {

    public static void main(String[] args) {
//        VN e = new VN("E");
//        VN t = new VN("T");
//        VN f = new VN("F");
//        VT et = new VT("E+T");
//        VT tt = new VT("T");
//        VT tf = new VT("T*F");
//        VT ee = new VT("(E)");
//        VT i = new VT("i");
//        VT ff = new VT("F");
//        
//        P prod1 = new P(e);
//        prod1.addConsecuente(et);
//        prod1.addConsecuente(tt);
//        
//        P prod2 = new P(t);
//        prod2.addConsecuente(tf);
//        prod2.addConsecuente(ff);
//        
//        P prod3 = new P(f);
//        prod3.addConsecuente(ee);
//        prod3.addConsecuente(i);
//        
//        G gramatica = new G(e);
//        
//        gramatica.add(e);
//        gramatica.add(t);
//        gramatica.add(f);
//        
//        gramatica.add(et);
//        gramatica.add(tt);
//        gramatica.add(tf);
//        gramatica.add(ff);
//        gramatica.add(ee);
//        gramatica.add(i);
//        
//        gramatica.add(prod1);
//        gramatica.add(prod2);
//        gramatica.add(prod3);

        VN paseo = new VN("PASEO");
        VN paso = new VN("PASO");
        VN exp = new VN("EXP");
        VT casa = new VT("casa");
        VT giro = new VT("giro");
        VT avanza = new VT("avanza");
        VT pinta = new VT("pinta");
        VT id = new VT("id");
        VT n = new VT("n");
        VT igual = new VT("=");

        P prod1 = new P(paseo);
        prod1.addConsecuente(paso);
        prod1.addConsecuente(paseo);

        P prod2 = new P(paso);
        prod2.addConsecuente(casa);

        P prod3 = new P(paso);
        prod3.addConsecuente(giro);
        prod3.addConsecuente(exp);

        P prod4 = new P(paso);
        prod4.addConsecuente(avanza);
        prod4.addConsecuente(exp);

        P prod5 = new P(paso);
        prod5.addConsecuente(pinta);
        prod5.addConsecuente(exp);

        P prod6 = new P(paso);
        prod6.addConsecuente(id);
        prod6.addConsecuente(igual);
        prod6.addConsecuente(exp);

        P prod7 = new P(exp);
        prod7.addConsecuente(id);

        P prod8 = new P(exp);
        prod8.addConsecuente(n);

        G gramatica = new G(paseo);

        gramatica.add(paseo);
        gramatica.add(paso);
        gramatica.add(exp);

        gramatica.add(casa);
        gramatica.add(giro);
        gramatica.add(avanza);
        gramatica.add(pinta);
        gramatica.add(id);
        gramatica.add(n);
        gramatica.add(igual);

        gramatica.add(prod1);
        gramatica.add(prod2);
        gramatica.add(prod3);
        gramatica.add(prod4);
        gramatica.add(prod5);
        gramatica.add(prod6);
        gramatica.add(prod7);
        gramatica.add(prod8);

        System.out.print(gramatica);
    }
}
