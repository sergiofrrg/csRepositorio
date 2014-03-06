/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author SergioFrrg
 */
public abstract class AnalizadorSintactico {
    G g;

    public AnalizadorSintactico(G g) {
        this.g = g;
    }
    
    public abstract boolean analiza(List <Token> vt); //COLLECTION
}
