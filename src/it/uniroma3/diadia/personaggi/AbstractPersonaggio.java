package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

//Classe astratta per la rappresentazione di un personaggio nel gioco
public abstract class AbstractPersonaggio {
	private boolean haSalutato;
	private String presentazione;
	private String nome;
	
	public AbstractPersonaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");

		risposta.append(this.getNome()+".");
		if (!haSalutato) {
			risposta.append(" " + this.presentazione);
		}
		else {
			risposta.append(" Ci siamo già presentati!");
		}
		
		this.haSalutato = true;
		return risposta.toString();
	}
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	abstract public String agisci(Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
