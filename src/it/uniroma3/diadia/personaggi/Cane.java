package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

//Classe che rappresenta un personaggio di tipo Cane nel gioco
public class Cane extends AbstractPersonaggio {
	private static final String CIBO_PREFERITO = "osso";
	private static final String MESSAGGIO_DEFAULT = "BAU! BAU! BAU!";
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		return MESSAGGIO_DEFAULT;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risposta = new StringBuilder("BAU grazie per avermi regalato ");

		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			risposta.append(attrezzo.getNome() + ", è il mio cibo preferito! Tieni una palla");
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("palla", 1));
		} 
		else {
			risposta.append(attrezzo.getNome() + ", ma non mi piace, BAU! BAU!");
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		}

		return risposta.toString();
	}
	
}
