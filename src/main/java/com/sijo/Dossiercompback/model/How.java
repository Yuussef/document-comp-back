package com.sijo.Dossiercompback.model;

public interface How {

    public default void sayHow(String how) throws RuntimeException{
        sayHow(how,1);
        try{
            if(null==how) throw  new RuntimeException("again ");
        }catch (RuntimeException rte){

        }
    }
public  void sayHow(String how,int n);
}
