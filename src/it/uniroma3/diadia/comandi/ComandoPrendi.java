package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/* Classe che gestisce il comando prendi */

public class ComandoPrendi extends AbstractComando {
	
	private String nomeAttrezzo;
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public void esegui (Partita partita) {
		nomeAttrezzo = getParametro();
		
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
					io.mostraMessaggio("L'attrezzo non è stato trovato");
				}
				else {
					if(partita.getGiocatoreBorsa().addAttrezzo(attrezzo)) {
						partita.getStanzaCorrente().removeAttrezzo(attrezzo);
						io.mostraMessaggio(nomeAttrezzo+" aggiunto all'inventario\n"+partita.getGiocatoreBorsa());
					}
					else {
						io.mostraMessaggio("Non c'è abbastanza spazio nella borsa, non posso aggiungere l'attrezzo");	
					}
				}
			}
		}
	}
}
