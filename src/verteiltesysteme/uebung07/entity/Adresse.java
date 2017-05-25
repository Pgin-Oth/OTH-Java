/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteiltesysteme.uebung07.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 陈香甦
 */
@XmlRootElement
public class Adresse {
    private String strasse;
    private String ort;

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
    
}
