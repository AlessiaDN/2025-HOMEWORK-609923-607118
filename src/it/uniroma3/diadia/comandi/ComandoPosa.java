package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/* Classe che gestisce il comando posa */

public class ComandoPosa implements Comando {
	
	private IO io;
	private String nomeAttrezzo;
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void setParametro (String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
	
	@Override
	public void esegui (Partita partita) {

		if (nomeAttrezzo == null) {
			io.mostraMessaggio("Nessun attrezzo inserito");
		} 
		else {
			if (partita.getGiocatoreBorsa().isEmpty()) {
				io.mostraMessaggio("La borsa non ha attrezzi, nulla da posare");
			} 
			else {
				Attrezzo attrezzo = partita.getGiocatoreBorsa().getAttrezzo(nomeAttrezzo);
				if (attrezzo == null) {
					io.mostraMessaggio("Attrezzo non trovato\n" + partita.getGiocatoreBorsa());
				} 
				else {
					if (partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
						partita.getGiocatoreBorsa().removeAttrezzo(attrezzo);
						io.mostraMessaggio(
								nomeAttrezzo + " aggiunto alla stanza\n" + partita.getGiocatoreBorsa());
					} 
					else {
						io.mostraMessaggio("Non posso aggiungere l'attrezzo perché non c'è abbastanza spazio nella stanza");
					}
				}
			}
		}
	}
}
