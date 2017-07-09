package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	public List<Integer> anni;
	private ArtsmiaDAO dao;
	private DirectedGraph<Mostra, DefaultEdge> grafo;
	private List<Mostra> mostre;
	private Map<Integer, Mostra> idMapMostre;
	private Simulatore sim;
	private List<Studente> classifica;
	
	
	public Model(){
		this.dao=new ArtsmiaDAO();
		this.idMapMostre=new HashMap<>();
	}
	
	public DirectedGraph<Mostra, DefaultEdge> getGrafo(int anno){
		
		if(grafo==null || grafo.vertexSet().isEmpty() || grafo.edgeSet().isEmpty())
			this.creaGrafo(anno);
		
		return this.grafo;
	}

	public List<Integer> getAnni() {
		if(this.anni==null)
			this.anni=this.dao.getListAnni();
		return this.anni;
	}

	public void creaGrafo(Integer anno) {

		this.grafo= new SimpleDirectedGraph<>(DefaultEdge.class);
		
		
		this.mostre= this.dao.getListMostre(anno, idMapMostre);
		
		
		Graphs.addAllVertices(this.grafo, this.mostre);
		
		for (Mostra m1 : grafo.vertexSet()) {
			for (Mostra m2 : grafo.vertexSet()) {
				if(!m1.equals(m2)){
					if(m1.getBegin()<m2.getBegin() && m1.getEnd()>m2.getBegin())
						grafo.addEdge(m1, m2);
					
					
				}
					
			}
		}
		
		
		System.out.println("Grafo creato!");
		System.out.println("# vertici: "+grafo.vertexSet().size());
		System.out.println("# archi: "+grafo.edgeSet().size());
		
	}

	public String isStronglyConnected() {

		StrongConnectivityAlgorithm<Mostra, DefaultEdge> ca = new KosarajuStrongConnectivityInspector<Mostra,DefaultEdge>(grafo);
		
		if(ca.isStronglyConnected())
			return "si";
		else
			return "no";
	}

	public MigliorMostra getMigliorMostra(Integer anno) {

		int max=0;
		int temp=0;
		
		MigliorMostra best=null;
		
		for (Mostra m : grafo.vertexSet()) {
			temp=dao.getOggettiMostra(m);
			
			if(temp>max){
				max=temp;
				best=new MigliorMostra(m, max);
			}
		}
		
		
		
		
		return best;
	}

	public void simulazione(Integer anno, Integer n) {

		classifica=new LinkedList<>();
		
		sim = new Simulatore(this.getGrafo(anno), n, anno, classifica);
		
		sim.simula();
		
		
	}

	public List<Studente> getSimResults() {
	 
		
		
		return this.classifica;
	}

	

}
