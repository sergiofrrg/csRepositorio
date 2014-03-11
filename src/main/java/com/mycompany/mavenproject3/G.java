package com.mycompany.mavenproject3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

public class G {

    private Collection<VN> vn;
    private Collection<VT> vt;
    private Hashtable<VN, Collection<V>> p;
    private VN s;

    public G(VN s) {
        this.vn = new ArrayList();
        this.vt = new ArrayList();
        this.p = new Hashtable();
        this.s = s;
    }

    public void add(VN elem) {
        this.vn.add(elem);
    }

    public void add(VT elem) {
        this.vt.add(elem);
    }

    public void add(P elem) {
        this.p.put(elem.getAntecedente(), elem.getConsecuente());
    }

    public String toString() {
        return ("G=(" + vn + ", " + vt + ", P, " + s + ")\nP=" + p);
    }
}
