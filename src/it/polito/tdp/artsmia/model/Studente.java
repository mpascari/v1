package it.polito.tdp.artsmia.model;

import java.util.Set;

import it.polito.tdp.artsmia.model.*;

public class Studente implements Comparable<Studente>{
	
	private int idStudente;
	private Set<Integer> idOpereViste;
	
	
	
	public Studente(int idStudente) {
		super();
		this.idStudente = idStudente;
	}
	
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public Set<Integer> getIdOpereViste() {
		return idOpereViste;
	}
	public void setIdOpereViste(Set<Integer> idOpereViste) {
		this.idOpereViste = idOpereViste;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStudente;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (idStudente != other.idStudente)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Studente [idStudente=" + idStudente + ". Numero opere viste: "+ idOpereViste.size();
	}
	@Override
	public int compareTo(Studente o) {
	 
		return this.idOpereViste.size()-o.idOpereViste.size();
	}
	
	
	
	
	
	

}
