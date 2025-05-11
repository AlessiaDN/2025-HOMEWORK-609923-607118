package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/* Classe che gestisce il comando guarda */

public class ComandoGuarda implements Comando{
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
		return "guarda";
	}
	
	@Override
	public void esegui (Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("\nHai ancora: "+partita.getGiocatore().getCfu()+ "CFU");
		io.mostraMessaggio(partita.getGiocatoreBorsa().toString());
	}
}
