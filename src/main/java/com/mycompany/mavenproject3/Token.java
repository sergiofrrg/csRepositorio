/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;



public class Token {

    String lexema;
    Object contenido;

    public Token(String a, Object b) {
        lexema = a;
        contenido = b;
    }

    public Token(String a) {
        lexema = a;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }

    public String toString() {
        if (contenido == null) {
            return ("<" + lexema + ">");
        } else {
            return ("<" + lexema + ", " + contenido.toString() + ">");
        }
    }

    public boolean compararLexema(String s) {
        return (this.lexema.compareTo(s) == 0);
    }
}
