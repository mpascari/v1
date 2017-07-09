/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Integer anno = this.boxAnno.getValue();
    	
    	if(anno==null){
    		txtResult.setText("Selezionare un anno dallla lista");
    		return;
    	}

    	try{
    	
    	txtResult.setText("sto chiedendo di creare il grfo per l'anno "+anno);
    	
    		model.creaGrafo(anno);
    		txtResult.setText("Il grafo è stato creato!");
    		txtResult.setText("Il grafo è connesso? "+model.isStronglyConnected()+"\n");
    	 	txtResult.appendText(model.getMigliorMostra(anno).toString());
    	}catch(RuntimeException e){
   		txtResult.setText("Qualcosa è andato storto nella creazione del grafo!");
    	}
    }

    @FXML
    void handleSimula(ActionEvent event) {

    	
    	txtResult.clear();
    	
    	Integer N =null;
    	Integer anno = this.boxAnno.getValue();
    	if(anno==null){
    		txtResult.setText("Selezionare un anno dallla lista");
    		return;
    	}
    	
    	try{
    		 N = Integer.valueOf(this.txtFieldStudenti.getText());
    		
    		if(N<=0)
    			throw new NumberFormatException();
    		
    	}catch(NumberFormatException e){
    		txtResult.setText("Inserire un numero di studenti (solo numeri interi maggiori di 0)");
    		return;
    	}
    	
//    	try{
    		
    		this.model.simulazione(anno, N);
    		List<Studente> classifica = this.model.getSimResults();
    		txtResult.setText(classifica.toString());
    		
//    	}catch(RuntimeException e){
//    		txtResult.setText("La simulazione è andata male");
//    		return;
//    		
//    	}
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {

		this.model=model;
		
		this.boxAnno.getItems().addAll(this.model.getAnni());
	}
}
