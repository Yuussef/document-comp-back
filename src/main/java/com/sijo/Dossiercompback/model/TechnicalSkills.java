package com.sijo.Dossiercompback.model;

import lombok.Data;

@Data
public class TechnicalSkills {
     String title;
     String label;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
