package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

import java.util.Queue;
import java.util.Random;

public class Simulatore {
	
	private List<Studente> studenti;
	private DirectedGraph<Mostra, DefaultEdge> grafo;
	private Integer N;
	private Integer annoPartenza;
	private ArtsmiaDAO dao;

	public Simulatore(DirectedGraph<Mostra, DefaultEdge> grafo, Integer N, Integer annoPartenza, List<Studente> classifica) {
		
		this.studenti=classifica;
		this.grafo=grafo;
		this.N=N;
		this.annoPartenza=annoPartenza;
		this.dao=new ArtsmiaDAO();
		

	}

	public void simula() {
		
		Queue<Event> queue = new LinkedList<Event>(); 
		
		//per ogni esibizione presente nel grafo aggiungo una lista delle sue opere
		for (Mostra m : grafo.vertexSet()) {
			m.setIdOperePresentate(this.dao.getListaIdOpere(m));
		}
		
		//selezione delle mostre attive quest anno
		List<Mostra> mostreAttive = new ArrayList<>();
		for (Mostra mostra : grafo.vertexSet()) {
			if(mostra.getBegin()==annoPartenza)
				mostreAttive.add(mostra);
		}
		
		//scelta casuale della mostra iniziale
		Random rand = new Random();
		int randInt= rand.nextInt(mostreAttive.size());
		Mostra prossimaMostra = mostreAttive.get(randInt);
		
		//fase di generazione dei studenti
		for (int i=0; i<this.N;i++) {
			Studente studente = new Studente(i+1);
			this.studenti.add(studente);
			
			//la fase di caricamento della coda prima della simulazione la includo nella simulazione stessa
			queue.add(new Event(studente, prossimaMostra));
		}
		
		
		
		//ciclo che scarica la coda e genera nuovi eventi
		while(!queue.isEmpty()){
			Event evento = queue.poll();
			
			//aggiungo allo studente le opere di questa mostra
			evento.getStudente().setIdOpereViste(evento.getMostra().getIdOperePresentate());
			
			//controllo se la simulazione deve andare avanti
			if(grafo.outDegreeOf(evento.getMostra())>0){
				//crea un nuovo evento ed aggiungilo alla coda degli eventi
				randInt= rand.nextInt(grafo.outDegreeOf(evento.getMostra()));
				
				//dalla lista dei vicini ne prendo uno a caso
				prossimaMostra=Graphs.successorListOf(grafo, evento.getMostra()).get(randInt);
				queue.add(new Event(evento.getStudente(), prossimaMostra));
			}
				
			
		}
		
		
		
		
		
		
		
		
	}

	
	
	
	

}
