package com.sijo.Dossiercompback.model;

public class Person extends  User {
    public void start (){
        sayHow(null);
    }
    @Override
    public  void sayHow(String how,int n){
        try{
            if(null==how) throw  new RuntimeException("again person");
        }catch (RuntimeException e){
            System.out.println("person");
        }
    }
}
