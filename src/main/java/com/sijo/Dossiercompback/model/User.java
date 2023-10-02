package com.sijo.Dossiercompback.model;

public class User implements How{
    @Override
    public void sayHow(String how, int n) {
        try{
            if(null==how) throw  new RuntimeException("again user");
        }catch (RuntimeException e){
            System.out.println("user");
        }
    }
}
