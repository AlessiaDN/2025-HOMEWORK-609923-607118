package it.uniroma3.diadia.comandi;

/* Interfaccia che permette la costruzione di un comnado */

public interface FabbricaDiComandi {
	public Comando costruisciComando(String istruzione);
}