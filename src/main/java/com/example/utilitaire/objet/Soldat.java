package com.example.utilitaire.objet;

import java.util.List;

public class Soldat {
    String name;
    int hp;
    public void Soldat(String n, int s){
        name=n;
        hp=s;
    }
    public String Matricule(){
        return name;
    }
    public int statut(){
        return hp;
    }
}