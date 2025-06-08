package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{
	
	@Override
	public String getNome() {
		return "saluta";
	}
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()!=null)
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else 
			io.mostraMessaggio("Non c'è alcun personaggio in questa stanza!");
	}
}
