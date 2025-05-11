package it.uniroma3.diadia;

/* Interfaccia per disaccoppiare I/O ed estrarre IOConsole */

public interface IO {
	public void mostraMessaggio(String messaggio);
	public String leggiRiga();
}
