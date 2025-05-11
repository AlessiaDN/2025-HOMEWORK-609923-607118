package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/* Classe che gestisce un comando non valido */

public class ComandoNonValido implements Comando {
	private IO io;
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void setParametro (String parametro) {

	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return "non valido";
	}
	
	@Override
	public void esegui (Partita partita) {
		io.mostraMessaggio("Hai inserito un comando non valido");
	}
}
