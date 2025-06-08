package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/* Classe che gestisce il comando guarda */

public class ComandoGuarda extends AbstractComando{
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public void esegui (Partita partita) {
		if(this.getParametro()!=null && this.getParametro().toLowerCase().equals("borsa")) {
			io.mostraMessaggio(partita.getGiocatoreBorsa().getContenutoOrdinato());
		}
		else {
			io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			io.mostraMessaggio("\nHai ancora: "+partita.getGiocatore().getCfu()+ "CFU");
			io.mostraMessaggio(partita.getGiocatoreBorsa().toString());
		}
	}
}
