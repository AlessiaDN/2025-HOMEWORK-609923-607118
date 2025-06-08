package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_SALUTATA = "Bravo, ti sei ricordato di salutarmi... ora vai!";
	private static final String MESSAGGIO_NON_SALUTATA = "Non mi hai nemmeno salutato... stanza con meno attrezzi per te!";
	
	public Strega(String nome, String present) {
		super(nome, present);
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "Non avrai pensato di corrompermi?";
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		List<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getStanzeAdiacenti();

		Stanza maxAtrezzi = stanzeAdiacenti.get(0);
		Stanza minAtrezzi = stanzeAdiacenti.get(0);

		for(Stanza s : stanzeAdiacenti) {
			if(s != null) {
				if(s.getNumeroAttrezzi() > maxAtrezzi.getNumeroAttrezzi())
					maxAtrezzi = s;
				if(s.getNumeroAttrezzi() < minAtrezzi.getNumeroAttrezzi())
					minAtrezzi = s;
			}
		}

		if(this.haSalutato()) {
			partita.setStanzaCorrente(maxAtrezzi);
			msg = MESSAGGIO_SALUTATA;
		} 
		else {
			partita.setStanzaCorrente(minAtrezzi);
			msg = MESSAGGIO_NON_SALUTATA;
		}

		return msg;
	}
}
