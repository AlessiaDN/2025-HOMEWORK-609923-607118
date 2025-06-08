package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/* Classe che gestisce il comando fine */

public class ComandoFine extends AbstractComando {
	
	@Override
	public String getNome() {
		return "fine";
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
