package com.sijo.Dossiercompback.model;

import java.util.List;

public class Resume {

	String name;

	String color;
	String job;
	String experience;
	String annee;
	String titrediplome;
	String ecole;
	String annee2;
	String titrediplome2;
	String ecole2;
	String skills;
	List<TechnicalSkills> technicalSkills;

	public String getJob() {
		return job;
	}

	public String getAnnee2() {
		return annee2;
	}

	public void setAnnee2(String annee2) {
		this.annee2 = annee2;
	}

	public String getTitrediplome2() {
		return titrediplome2;
	}

	public void setTitrediplome2(String titrediplome2) {
		this.titrediplome2 = titrediplome2;
	}

	public String getEcole2() {
		return ecole2;
	}

	public void setEcole2(String ecole2) {
		this.ecole2 = ecole2;
	}

	public String getExperience() {
		return experience;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getTitrediplome() {
		return titrediplome;
	}

	public void setTitrediplome(String titrediplome) {
		this.titrediplome = titrediplome;
	}

	public String getEcole() {
		return ecole;
	}

	public void setEcole(String ecole) {
		this.ecole = ecole;
	}

	public List<TechnicalSkills> getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(List<TechnicalSkills> technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
}
