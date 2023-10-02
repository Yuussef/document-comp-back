package com.sijo.dossiercomp.model;

import lombok.Data;

import java.util.List;

@Data
public class Resume {

	private String name;
	private String color;
	private String job;
	private String experience;
	private String annee;
	private String titrediplome;
	private String ecole;
	private String annee2;
	private String titrediplome2;
	private String ecole2;
	private String skills;
	private List<TechnicalSkills> technicalSkills;

}
