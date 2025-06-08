package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

//Classe astratta con il fine di fornire una base comune per implementare i comandi
public abstract class AbstractComando implements Comando {
	protected String parametro;
	protected IO io;
	abstract public void esegui(Partita partita);
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
	
	@Override
    public String getParametro() {
        return this.parametro;
    }
}
