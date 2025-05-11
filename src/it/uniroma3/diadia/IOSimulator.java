package it.uniroma3.diadia;


/* Classe per creare un simulatore di console */


public class IOSimulator implements IO{
	private String[] input;
	private int indiceInput;

	private String[] output;
	private int indiceOutput;
	
	public IOSimulator(String[] inputUtente) {
		this.input = inputUtente;
		this.indiceInput = 0;
		this.output = new String[10];
		this.indiceOutput = 0;
	}
	
	@Override
	public String leggiRiga() {
	    if (indiceInput >= input.length) {
	        return "fine"; //Forza il comando di terminazione se la stringa comandi non lo passa in input
	    }
	    return this.input[indiceInput++];
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		if(indiceOutput>=output.length) {
			espandiArrayOutput();
		}
		this.output[indiceOutput++] = msg;
	}
	
	private void espandiArrayOutput() {
        String[] nuovoOutput = new String[output.length * 2];
        //raddoppia la dimensione dell'array se pieno
        for (int i = 0; i < output.length; i++) {
            nuovoOutput[i] = output[i];
        }
        output = nuovoOutput;
    }
	
	public boolean contieneMessaggio(String messaggio) {
        this.output = getOutput();
		
		for(String s : this.output) {
            if(s.contains(messaggio)) return true;
        }
        return false;
    }
	
	public String[] getOutput() {
		String[] output = new String[indiceOutput];
		
		for(int i=0; i<indiceOutput; i++) {
			output[i] = this.output[i];
		}
		return output;
	}
}
