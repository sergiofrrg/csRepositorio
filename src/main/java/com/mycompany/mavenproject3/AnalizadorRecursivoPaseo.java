/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SergioFrrg
 */
public class AnalizadorRecursivoPaseo extends AnalizadorSintactico {

    Token tokenActual;
    int posicion = 0;
    List<Token> col;

    public AnalizadorRecursivoPaseo(G g) {
        super(g);
    }

    @Override
    public boolean analiza(List<Token> vt) {
        col = vt;
        System.out.println(col.get(posicion + 1).getLexema() + " " + col.get(posicion + 1).getContenido());
        tokenActual = new Token(col.get(posicion).getLexema(), col.get(posicion).getContenido());
        try {
            paseo();
        } catch (Exception e) {
            System.out.println("Error de analiza");
        }
        return tokenActual.compararLexema("TK_FIN_SENT");
    }

    public void paseo() {
        if (esCasaGiroAvanzaPintaID()) {
            try {
                paso();
            } catch (Exception ex) {
                Logger.getLogger(AnalizadorRecursivoPaseo.class.getName()).log(Level.SEVERE, null, ex);
            }
            paseo();
        }
    }

    public boolean esCasaGiroAvanzaPintaID() {
        return (esCasa() || esPinta() || esAvanza() || esID() || esGiro());
    }

    public void paso() throws Exception {
        if (esCasa()) {
            match(new VT("TK_CASA"));
            return;
        } else if (esGiro()) {

            match(new VT("TK_GIRO"));
            Exp();
            return;
        } else if (esAvanza()) {
            match(new VT("TK_AVANZA"));
            Exp();
            return;
        } else if (esPinta()) {
            match(new VT("TK_PINTA"));
            Exp();
            return;
        } else if (esID()) {
            match(new VT("TK_ID"));
            match(new VT("TK_ASIG"));
            Exp();
            return;

        }
        throw new Exception("Error");
    }

    public void Exp() throws Exception {
        if (esID()) {
            match(new VT("TK_ID"));
            return;
        } else if (esN()) {
            match(new VT("TK_CTE_NUM"));
            return;
        }

        throw new Exception("esperaba id o numero");
    }

    public void match(VT lexema) {

        if (tokenActual.compararLexema(lexema.getV())) {
            if (posicion + 1 != col.size()) {
                ++posicion;
                tokenActual = new Token(col.get(posicion).getLexema(), col.get(posicion).getContenido());
            } else {
                tokenActual = new Token("fin");      //Tenerlo en cuenta en analiza para capturar el final
            }

            return;

        }
        try {
            throw new Exception("");
        } catch (Exception ex) {
            Logger.getLogger(AnalizadorRecursivoPaseo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean esN() {
        return (tokenActual.compararLexema("TK_CTE_NUM"));
    }

    public boolean esID() {          //Arreglar todos dependiendo del lexico

        if (tokenActual.compararLexema("TK_ID")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean esCasa() {          //Arreglar todos dependiendo del lexico

        if (tokenActual.compararLexema("TK_CASA")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean esGiro() {

        if (tokenActual.compararLexema("TK_GIRO")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean esAvanza() {

        if (tokenActual.compararLexema("TK_AVANZA")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean esPinta() {

        if (tokenActual.compararLexema("TK_PINTA")) {
            return true;
        } else {
            return false;
        }
    }
}
