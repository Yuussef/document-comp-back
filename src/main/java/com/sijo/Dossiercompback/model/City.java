package com.sijo.Dossiercompback.model;

import lombok.Getter;

public class City implements Comparable<City> {
    @Getter
    private String name ;
    private boolean isCapital;


    public City(String name, boolean isCapital) {
        this.name = name;
        this.isCapital = isCapital;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    @Override
    public int compareTo(City other) {
        // comparison logic here
        // for example, if you have a name field, you might do:
        return this.name.compareTo(other.name);
    }
    @Override
    public String toString() {
        // return a string representation of the object
        // for example, if your City has a name field, you might do:
        return name;
    }
}
