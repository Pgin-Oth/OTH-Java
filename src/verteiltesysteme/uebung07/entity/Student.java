/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteiltesysteme.uebung07.entity;

import verteiltesysteme.uebung07.entity.Adresse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 陈香甦
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private int martikelnr;
    private String vornamen;
    private String nachnamen;
    private int ects;
    private Adresse adresse;

    public int getMartikelnr() {
        return martikelnr;
    }

    public void setMartikelnr(int martikelnr) {
        this.martikelnr = martikelnr;
    }

    @Override
    public String toString() {
        return "Student{" + "martikelnr=" + martikelnr + ", vornamen=" + vornamen + ", nachnamen=" + nachnamen + ", ects=" + ects + ", adresse=" + adresse + '}';
    }
    
}
