/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

/**
 *
 * @author SergioFrrg
 */
public class PracticaOblPL {

    /**
     * @param args the command line arguments
     */
    public static G crearGramatica() {
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


        return gramatica;
    }

    public static void main(String[] args) {
        AnalizadorLexico al = new AnalizadorLexico();
        G g = crearGramatica();
        AnalizadorRecursivoPaseo arp = new AnalizadorRecursivoPaseo(g);
        //System.out.println(al.analizar("15 156;").toString());
        if (arp.analiza(al.analizar("a=15;"))) {
            System.out.println("Pues está correcto");
        } else {
            System.out.println("Pues está mal");
        }

    }
}
