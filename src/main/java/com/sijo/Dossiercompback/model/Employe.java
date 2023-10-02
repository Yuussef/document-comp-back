package com.sijo.Dossiercompback.model;

public class Employe extends  Person{
    @Override
    public  void sayHow(String how,int n){
        try{
            if(null==how) throw  new RuntimeException("again Employe");
        }catch (RuntimeException e){
            System.out.println("employe");
        }
    }
}
