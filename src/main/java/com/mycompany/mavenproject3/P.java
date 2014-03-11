package com.mycompany.mavenproject3;

import java.util.ArrayList;
import java.util.Collection;

public class P {

    private VN antecedente;
    private Collection<V> consecuente;

    public VN getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(VN antecedente) {
        this.antecedente = antecedente;
    }

    public Collection<V> getConsecuente() {
        return consecuente;
    }

    public void setConsecuente(Collection<V> consecuente) {
        this.consecuente = consecuente;
    }

    public P(VN antecedente) {
        this.antecedente = antecedente;
        this.consecuente = new ArrayList<V>();
    }

    public void addConsecuente(V consecuente) {
        this.consecuente.add(consecuente);
    }

    public String toString() {
        return (antecedente + "->" + consecuente);
    }
}
