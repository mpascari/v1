package it.polito.tdp.artsmia.model;

public class MigliorMostra {
	
	private Mostra m;
	private int oggetti;
	
	public MigliorMostra(Mostra m, int oggetti) {
		super();
		this.m = m;
		this.oggetti = oggetti;
	}
	public Mostra getM() {
		return m;
	}
	public void setM(Mostra m) {
		this.m = m;
	}
	public int getOggetti() {
		return oggetti;
	}
	public void setOggetti(int oggetti) {
		this.oggetti = oggetti;
	}
	
	@Override
	public String toString() {
		
		return "MigliorMostra [m=" + m.getTitolo() + ", oggetti=" + oggetti + "]";
	}
	
	

}
