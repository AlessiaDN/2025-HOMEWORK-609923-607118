package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/* Interfaccia che gestisce l'esecuzione dei programmi */

public interface Comando {
	
	public void esegui(Partita partita);       //esecuzione comando
	
	public void setParametro(String parametro);       //set parametro comando
	
	public void setIO(IO io);     //set per la console IO
	
	public String getNome();     //get per ottenere il nome del comando
	
	public String getParametro();      //get per ottenere il nome del parametro passato al comando	
}