package it.uniroma3.diadia;


import java.util.List;
import java.util.Map;
import java.util.HashMap;



/* Classe per creare un simulatore di console */


public class IOSimulator implements IO{
	private List<String> input;
	private int indiceInput;

	private Map<Integer, String> output;
	private int indiceOutput;
	
	public IOSimulator(List<String> inputUtente) {
		this.input = inputUtente;
		this.indiceInput = 0;
		this.output = new HashMap<Integer, String>();
		this.indiceOutput = 0;
	}
	
	@Override
	public String leggiRiga() {
	    if (indiceInput >= input.size()) {
	        return "fine"; 
	    }
	    return this.input.get(indiceInput++);
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		this.output.put(indiceOutput++, msg);
	}
	
	public boolean contieneMessaggio(String messaggio) {
		
		for(String msg : this.output.values()) {
            if(msg.contains(messaggio)) {
            	return true;
            }
        }
        return false;
    }
	
	public String contieneMessaggioAtIndice(Integer indice) {
		return this.output.get(indice);
	}
}
