/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.RDV;

import java.util.List;

/**
 *
 * @author neder
 */
public interface IService <T> {
    public void ajouterRdv(RDV R);
    public List<RDV>affichageRdv ();
    public void supprimerRdv(int id);
    public RDV getRdv(int id);
    public void modifierRdv(RDV R);
     
}
