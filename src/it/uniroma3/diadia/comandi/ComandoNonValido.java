package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/* Classe che gestisce un comando non valido */

public class ComandoNonValido extends AbstractComando {
	
	@Override
	public String getNome() {
		return "non valido";
	}
	
	@Override
	public void esegui (Partita partita) {
		io.mostraMessaggio("Hai inserito un comando non valido");
	}
}
