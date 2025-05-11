package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/* Classe che gestisce il comando prendi */

public class ComandoPrendi implements Comando {
	
	private String nomeAttrezzo;
	private IO io;
	
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
		return "prendi";
	}
	
	@Override
	public void esegui (Partita partita) {

		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Nessun attrezzo inserito");
		}
		else {
			if(partita.getStanzaCorrente().isEmpty()) {
				io.mostraMessaggio("La stanza non ha attrezzi, nulla da raccogliere");
			}
			else {
				Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				if(attrezzo==null) {
					io.mostraMessaggio("Attrezzo non trovato");
				}
				else {
					if(partita.getGiocatoreBorsa().addAttrezzo(attrezzo)) {
						partita.getStanzaCorrente().removeAttrezzo(attrezzo);
						io.mostraMessaggio(nomeAttrezzo+" aggiunto all'inventario\n"+partita.getGiocatoreBorsa());
					}
					else {
						io.mostraMessaggio("Non posso aggiungere l'attrezzo perché non c'è abbastanza spazio nella borsa");	
					}
				}
			}
		}
	}
}
