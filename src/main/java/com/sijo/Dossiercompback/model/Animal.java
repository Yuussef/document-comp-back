package com.sijo.Dossiercompback.model;

import java.util.Objects;

public class Animal extends  Object {
    private String name;


    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      return((Animal) o).name==this.name;
              }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
