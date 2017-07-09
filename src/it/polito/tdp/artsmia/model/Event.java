package it.polito.tdp.artsmia.model;

public class Event {
	
	private Studente studente;
	private Mostra mostra;
	public Event(Studente studente, Mostra mostra) {
		super();
		this.studente = studente;
		this.mostra = mostra;
	}
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	public Mostra getMostra() {
		return mostra;
	}
	public void setMostra(Mostra mostra) {
		this.mostra = mostra;
	}
	
	

}
