package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/* Classe che gestisce il comando aiuto */

public class ComandoAiuto implements Comando {
	private IO io;
	static final private String[] elencoComandi = {"vai","fine","guarda","posa","prendi","aiuto" };
	
	@Override
	public void esegui (Partita partita) {
		for (int i = 0; i < elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i]);
		io.mostraMessaggio("");
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public void setParametro (String parametro) {
	}
}
