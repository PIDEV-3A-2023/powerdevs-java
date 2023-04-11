/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Ordonnance ;

import java.util.List;
/**
 *
 * @author neder
 */
public interface IServiceOrd <T>{
     public void ajouterOrd(Ordonnance O);
    public List<Ordonnance>affichageOrd();
    public void supprimerOrd(Ordonnance O);
    public Ordonnance getOrd(int id);
    public void modifierOrd(Ordonnance O );
}