/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SergioFrrg
 */
public class Interprete {
    
    /** Javadoc document **/

    Token tokenActual;
    int posicion = 0;
    List<Token> col;
    Tortuga t;
    Map<String, Double> variables = new TreeMap<String, Double>();;
    
    public Interprete (Tortuga t){
        this.t = t;
    }

    public Tortuga getT() {
        return t;
    }
    
    public void setT(Tortuga tortuga){
        this.t = tortuga;
    }
    
    public boolean analiza(List<Token> vt) {
            col=vt;
            tokenActual = new Token(col.get(posicion).getLexema(), col.get(posicion).getContenido());
        try{
            paseo();
        }catch(Exception e){
            System.out.println("Error de analiza");
        }
        return tokenActual.compararLexema("TK_FIN_SENT");
    }
    
    public void paseo(){
        if(esCasaGiroAvanzaPintaID()){
            try {
                paso();
            } catch (Exception ex) {
                Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
            }
            paseo();
        }
        else{
            if (this.match(new VT("TK_FIN_SENT"))||this.match(new VT("TK_SALTO_LINEA")))
                paseo();
            else
                return;
        }
    }
    
    public boolean esCasaGiroAvanzaPintaID(){
        return (esCasa()||esPinta()||esAvanza()||esID() || esGiro()||esIR()||esSI());
    }
    
    public void paso() throws Exception{
        if(esCasa())
        {
            match(new VT ("TK_CASA"));
            t.home();
            return;
        }else if(esGiro()){
            
            match(new VT ("TK_GIRO"));
            double d=E();
            t.turn(d);
            return;
        }else if(esAvanza()){
            match(new VT ("TK_AVANZA"));
            double d=E();
            t.move(d);
            return;
        }else if(esPinta()){
            match(new VT ("TK_PINTA"));
            double d=E();
            if(d==1.0)
                t.pen(true);
            else if(d==0)
                t.pen(false);
            else
                throw new Exception ("Error, tiene que ser 0 o 1");
            return;
        }else if(esID()){
            String nombreV=tokenActual.getContenido().toString();
            match(new VT ("TK_ID"));
            match(new VT("TK_ASIG"));
            double d=E();
            variables.put(nombreV, d);
            return;
            
        }else if(esSI()){
            match(new VT("TK_SI"));
            double sol=E();
            if(sol!=0){
                match(new VT("TK_ENTONCES"));
                paso();
            }else{
                while(!tokenActual.compararLexema("TK_FIN_SENT")){  //AVANZA TODOS LOS TOKENS QUE SOLO SE EJECUTARÍAN
                    String token=tokenActual.getLexema();           //CON UNA CONDICIÓN CORRECTA
                    match(new VT(token));
                }  
            }
            return;
        }else if(esIR()){
            match(new VT("TK_IR_A"));
            double d=E();
            irA(d);
            return;
        }
        throw new Exception ("Error");
    }
   
    
    public boolean match(VT lexema) {
        
        if (tokenActual.compararLexema(lexema.getV())) {
            if(posicion+1!=col.size()){
                ++posicion;
                tokenActual = new Token(col.get(posicion).getLexema(), col.get(posicion).getContenido());
                return true;
            }else{
                tokenActual=new Token("fin");   //Tenerlo en cuenta en analiza para capturar el final
            }
        }
        return false;
 
//        try {
//            throw new Exception("");
//        } catch (Exception ex) {
//            Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public boolean esN(){
        return(tokenActual.compararLexema("TK_CTE_NUM"));
    }
    
    public boolean esID(){          //Arreglar todos dependiendo del lexico
        
        if(tokenActual.compararLexema("TK_ID"))
            return true;
        else
            return false;
    }
    public boolean esCasa(){          //Arreglar todos dependiendo del lexico
        
        if(tokenActual.compararLexema("TK_CASA"))
            return true;
        else
            return false;
    }
    public boolean esGiro(){
        
        if(tokenActual.compararLexema("TK_GIRO"))
            return true;
        else
            return false;
    }
    
    public boolean esAvanza(){
        
        if(tokenActual.compararLexema("TK_AVANZA"))
            return true;
        else
            return false;
    }
    
    public boolean esPinta(){
        
        if(tokenActual.compararLexema("TK_PINTA"))
            return true;
        else
            return false;
    }
    
    public boolean esSR(){
        return (esS()||esR());
    }
    
    public boolean esS(){
        return (tokenActual.compararLexema("TK_MAS"));
    }
    
    public boolean esR(){
        return (tokenActual.compararLexema("TK_MENOS"));
    }
    
    public boolean esM(){
        return (tokenActual.compararLexema("TK_POR")); 
    }
    
    public boolean esD(){
        return (tokenActual.compararLexema("TK_ENTRE")); 
    }
    
    public boolean esMD(){
        return (esM()||esD()); 
    }
    
    public boolean esPA(){
        return (tokenActual.compararLexema("TK_PAR_ABR")); 
    }
    
    public boolean esPC(){
        return (tokenActual.compararLexema("TK_PAR_CER")); 
    }
    
    public boolean esSI(){
        return (tokenActual.compararLexema("TK_SI")); 
    }
    
    public boolean esIR(){
        return (tokenActual.compararLexema("TK_IR_A")); 
    }
    
    public boolean esMayor(){
        return (tokenActual.compararLexema("TK_MAYOR")); 
    }
    
    public boolean esMenor(){
        return (tokenActual.compararLexema("TK_MENOR")); 
    }
    
    public boolean esIgual(){
        return (tokenActual.compararLexema("TK_IGUAL")); 
    }
    
    public boolean esDistinto(){
        return (tokenActual.compararLexema("TK_DESIGUAL")); 
    }
    
    
    
    public double E() throws Exception{          //ARREGLAR LOS RETURN DE TODOS ESTOS (NO EVALUA)
        double d=T();                            
        if(esS())
            return d+Ep();
        if(esR())
            return d-Ep();
        if(esMayor()){
            String token=tokenActual.getLexema();
            match(new VT(token));
            double d2=E();
            if(d>d2)
                return 1;
            else 
                return 0;
        
        }
        
        if(esMenor()){
            String token=tokenActual.getLexema();
            match(new VT(token));
            double d2=E();
            if(d<d2)
                return 1;
            else 
                return 0;
        
        }
        
        if(esIgual()){
            String token=tokenActual.getLexema();
            match(new VT(token));
            double d2=E();
            if(d==d2)
                return 1;
            else 
                return 0;
        
        }
        
        if(esDistinto()){
            String token=tokenActual.getLexema();
            match(new VT(token));
            double d2=E();
            if(d!=d2)
                return 1;
            else 
                return 0;
        
        }
        return d;
    }
    
    public double Ep() throws Exception{
        if(esS()){
            match(new VT("TK_MAS"));
            double d=T();
            if(esS())
                return d+Ep();
            if(esR())
                return d-Ep();
            return d;
            
        }
        if (esR()){
            match(new VT("TK_MENOS"));
            double d=T();
            if(esS())
                return d+Ep();
            if(esR())
                return d-Ep();
            return d;
            
        }
        throw new Exception ("Error en EP");
    }
    
    public double T() throws Exception{
        double d = F();
        if(esM())
            return d*Tp();
        if(esD())
            return d/Tp();
        return d;
        
    }
    
    public double Tp() throws Exception{
        if(esM()){
            match(new VT("TK_POR"));
            double d = F();
            if (esM()) {
                return d * Tp();
            }
            if (esD()) {
                return d / Tp();
            }
            return d;
            
        }
        if (esD()){
            match(new VT("TK_ENTRE"));
            double d = F();
            if (esM()) {
                return d * Tp();
            }
            if (esD()) {
                return d / Tp();
            }
            return d;
            
        }
        throw new Exception ("Error en TP");
    }
    
    public double F() throws Exception{
        if(esPA()){
            match(new VT("TK_PAR_ABR"));
            double d=E();
            match(new VT("TK_PAR_CER"));
            return d;
        }
        if (esID()) {
            String clave=tokenActual.getContenido().toString();
            match(new VT("TK_ID"));
            return variables.get(clave) ;   //TIENE QUE DEVOLVER EL VALOR DE LA VARIABLE
        }
        else if (esN()) {
            double d=Double.parseDouble(tokenActual.getContenido().toString());
            match(new VT("TK_CTE_NUM"));
            return d;
        }
        if (esR()){
            
            match(new VT("TK_MENOS"));
            double d=Double.parseDouble(tokenActual.getContenido().toString());
            match(new VT("TK_CTE_NUM"));
            return -d;
            
        }
        
        if (esS()){
            match(new VT("TK_MAS"));
            double d=Double.parseDouble(tokenActual.getContenido().toString());
            match(new VT("TK_CTE_NUM"));
            return d;
        }
        throw new Exception("esperaba id o numero");
    
    }
    
    public void irA(double linea){
        int i = 0;
        double cont = 0;
        Token aux = this.col.get(i);
        while (i<this.col.size()&&cont!=linea){
            if (aux.compararLexema("TK_SALTO_LINEA"))
                cont++;
            i++;
            aux = this.col.get(i);
        }
        if (cont == linea){
            this.posicion = i;
            tokenActual=new Token(col.get(posicion).getLexema(), col.get(posicion).getContenido());
        }
    }
    
}
