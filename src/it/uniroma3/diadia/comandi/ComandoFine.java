package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/* Classe che gestisce il comando fine */

public class ComandoFine implements Comando {
	private IO io;
	
	@Override 
	public void setParametro (String parametro) {
		
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui (Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
		partita.setFinita();
	}
}
