package com.mycompany.mavenproject3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class AnalizadorLexico {
    final int LONG = 16;
    int p_observacion=-1;
    char buffer[] = new char[LONG];
    int estado = 0;
    int valor = 0;
    int digito = 0;
    char caracter;
    String cadena;
    int posEntrada = 0;
    List<Token> tokens = new ArrayList<Token>();
    Set listaPR = new HashSet();
    protected final static String L = "[a-zA-ZñÑ]*|\\_";
    protected final static String N = "\\d*";
    protected final static String DEL = "\\s|\t";
    protected final static String DEL1 = DEL+"|\\{|\\=|\\+|\\;|\\)|\\<|\\-|\\*|\\/|\\>";
    protected final static String DEL2 = DEL+"|\\(|[a-zA-ZñÑ]|\\d";

    public AnalizadorLexico(){
        this.listaPR.add("avanza");
        this.listaPR.add("pinta");
        this.listaPR.add("giro");
        this.listaPR.add("id");
        this.listaPR.add("casa");
        this.listaPR.add("ir_a");
        this.listaPR.add("si");
        this.listaPR.add("entonces");
    }
    public char leerSiguienteCaracter(String entrada){
//        if (p_observacion == LONG / 2 - 1) {
//            this.recargaBuffer(2, entrada);
//            p_observacion++;
//        } else {
//            if (p_observacion == LONG) {
//                this.recargaBuffer(1, entrada);
//                p_observacion = 0;
//            } else {
//                p_observacion++;
//            }
//        }
//        this.posEntrada++;
//        if (p_observacion==0){
//            return this.buffer[p_observacion];
//        }
        this.p_observacion++;
        try{
            char caracter = entrada.charAt(this.p_observacion);
        }
        catch(Exception e){
            return ' ';
        }
        return entrada.charAt(this.p_observacion);
    }
    
    public boolean esDelTipo(String c, String constante){
        return Pattern.compile(constante).matcher(c).matches();
    }
    public void retrocesoPuntero(){
        this.p_observacion--;
        this.posEntrada--;
    }
    
    public void recargaBuffer(int mitad, String entrada){
        int i;
        int tope;
        int aux;
        if (mitad==1){
            i = 0;
            tope = this.LONG/2-1;
            aux = this.posEntrada;
        }
        else{
            i=this.LONG/2;
            tope = this.LONG-1;
            aux = this.posEntrada+1;
        }
        while (i<=tope && aux<entrada.length()){
            this.buffer[i] = entrada.charAt(aux);
            aux++;
            i++;
        }      
    }
    
    public double convierteNumero (){
    double i = 0;
        try {
            i = Double.parseDouble(cadena);
        }
        catch (NumberFormatException e) {
            System.out.println("Error al parsear numero");
        }
        return i;
    }
    
    public void concatenarCaracter (char c){
        this.cadena = this.cadena+c;
    }
    
    public String daLexema(){
        return this.cadena;
    }
    
    public void iniLexema(){
        this.cadena = "";
        this.caracter = ' ';
    }
    
    private void diferPRId(String lexema) {
        tokens.add(listaPR.contains(lexema) ? new Token("TK_"+ lexema.toUpperCase()) : new Token("TK_ID", lexema));
    }
    
    public void daToken (Token token){
        tokens.add(token);
    }
    
    public List<Token> analizar(String entrada){       
        estado = 0;
        valor = 0;
        digito = 0;
        //this.recargaBuffer(1, entrada);
        String aux;
        while (this.p_observacion<entrada.length()){
            switch (estado){
                case 0:
                    this.iniLexema();
                    digito = 0;
                    valor = 0;
                    caracter = leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, DEL)){
                        estado = 0;
                    }
                    else if(this.esDelTipo(aux, L)){
                        estado = 1;
                    }
                    else if(this.esDelTipo(aux, N)){
                        estado = 16;
                    }
                    else{
                        switch (caracter) {
                            case '{':
                                estado = 5;
                                break;
                            case '}':
                                estado = 6;
                                break;
                            case ';':
                                estado = 9;
                                break;
                            case '(':
                                estado = 14;
                                break;
                            case ')':
                                estado = 15;
                                break;
                            case '=':
                                estado = 7;
                                break;
                            case '+':
                                estado = 8;
                                break;
                            case '-':
                                estado= 19;
                                break;
                            case '/':
                                estado = 20;
                                break;
                            case '*':
                                estado = 21;
                                break;
                            case '<':
                                estado = 10;
                                break;
                            case '>':
                                estado = 101;
                                break;
                            case '#':
                                estado = 18;
                                break;
                        }
                    }
                    break;
                case 1:
                    concatenarCaracter(caracter);
                    caracter = this.leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, DEL1)){
                        estado = 2;
                    }
                    else if (this.esDelTipo(aux, N)){
                        estado = 3;
                    }
                    else if (this.esDelTipo(aux, L)){
                        estado = 1;
                    }
                    else
                        estado = 2;
                    break;
                case 2:
                    this.retrocesoPuntero();
                    this.diferPRId(this.daLexema());
                    estado = 0;
                    break;
                case 3:
                    this.concatenarCaracter(caracter);
                    caracter = this.leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, DEL1)){
                        estado = 4;
                    }
                    else if (this.esDelTipo(aux, N)){
                        estado = 3;
                    }
                    else if (this.esDelTipo(aux, L)){
                        estado = 3;
                    }
                    break;
                case 4:
                    this.retrocesoPuntero();
                    this.daToken(new Token("TK_ID", this.daLexema()));
                    estado = 0;
                    break;
                case 5:
                    this.daToken(new Token("TK_LLAV_ABR"));
                    estado = 0;
                    break;
                case 6:
                    this.daToken(new Token("TK_LLAV_CER"));
                    estado = 0;
                    break;
                case 9:
                    this.daToken(new Token("TK_FIN_SENT"));
                    estado = 0;
                    break;
                case 14:
                    daToken(new Token("TK_PAR_ABR"));
                    estado = 0;
                    break;
                case 18:
                    daToken(new Token("TK_SALTO_LINEA"));
                    estado = 0;
                    break;
                case 15:
                    daToken(new Token("TK_PAR_CER"));
                    estado = 0;
                    break;
                case 7:
                    caracter = leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, DEL2)){
                        estado = 11;
                    }
                    else if (aux.contains("=")){
                        estado = 102;
                    }
                    break;
                case 8:
                    caracter = leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, DEL2)){
                        estado = 12;
                    }
                    break;
                case 19:
                    caracter = leerSiguienteCaracter(entrada);
                    aux = "" + caracter;
                    if (this.esDelTipo(aux, DEL2)) {
                        estado = 22;
                    }
                    break;
                case 20:
                    caracter = leerSiguienteCaracter(entrada);
                    aux = "" + caracter;
                    if (this.esDelTipo(aux, DEL2)) {
                        estado = 23;
                    }
                    break;
                case 21:
                    caracter = leerSiguienteCaracter(entrada);
                    aux = "" + caracter;
                    if (this.esDelTipo(aux, DEL2)) {
                        estado = 24;
                    }
                    break;
                case 10:
                    caracter = leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, DEL2)){
                        estado = 13;
                    }
                    else if(aux.contains(">")){
                        estado = 122;
                    }
                    break;
                case 101:
                    caracter = leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, DEL2)){
                        estado =131;
                    }
                    break;
                case 11:
                    retrocesoPuntero();
                    daToken(new Token("TK_ASIG"));
                    estado = 0;
                    break;
                case 12:
                    retrocesoPuntero();
                    daToken(new Token("TK_MAS"));
                    estado = 0;
                    break;
                case 22:
                    retrocesoPuntero();
                    daToken(new Token("TK_MENOS"));
                    estado = 0;
                    break;
                case 23:
                    retrocesoPuntero();
                    daToken(new Token("TK_ENTRE"));
                    estado = 0;
                    break;
                case 24:
                    retrocesoPuntero();
                    daToken(new Token("TK_POR"));
                    estado = 0;
                    break;
                case 13:
                    retrocesoPuntero();
                    daToken(new Token("TK_MENOR"));
                    estado = 0;
                    break;
                case 131:
                    retrocesoPuntero();
                    daToken(new Token("TK_MAYOR"));
                    estado = 0;
                    break;
                case 102:
                    retrocesoPuntero();
                    daToken(new Token("TK_IGUAL"));
                    estado = 0;
                    this.leerSiguienteCaracter(entrada);
                    break;
                case 122:
                    retrocesoPuntero();
                    daToken(new Token("TK_DESIGUAL"));
                    estado = 0;
                    this.leerSiguienteCaracter(entrada);
                    break;
                case 17:
                    retrocesoPuntero();
                    daToken(new Token("TK_CTE_NUM", Double.parseDouble(cadena)));
                    estado = 0;
                    break;
                case 16:
                    this.concatenarCaracter(caracter);
                    this.convierteNumero();
                    caracter = this.leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, N)){
                        estado = 16;
                    }
                    else if(this.esDelTipo(aux, DEL1)){
                        estado = 17;
                    }
                    else if (aux.contains(".")){
                        estado = 161;
                    }
                    break;
                case 162:
                    this.concatenarCaracter(caracter);
                    this.convierteNumero();
                    caracter = this.leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, N)){
                        estado = 162;
                    }
                    else if(this.esDelTipo(aux, DEL1)){
                        estado = 17;
                    }
                    break;
                case 161:
                    this.concatenarCaracter(caracter);
                    caracter = this.leerSiguienteCaracter(entrada);
                    aux = ""+caracter;
                    if (this.esDelTipo(aux, N)){
                        estado = 162;
                    }
                    else if(this.esDelTipo(aux, DEL1)){
                        estado = 17;
                    }
                    break;
            }
        }
        return this.tokens;
    }
}