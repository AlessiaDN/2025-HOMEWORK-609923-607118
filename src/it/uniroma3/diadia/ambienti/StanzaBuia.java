package it.uniroma3.diadia.ambienti;

/* Estensione della classe Stanza che permette di creare una stanza buia */
/* che non permette di leggere gli attrezzi presenti, le direzioni e la sua descrizione */

public class StanzaBuia extends Stanza {
	String Illumina;
	
	public StanzaBuia(String nome, String Illumina) {
		super(nome);
		this.Illumina = Illumina;
	}
	
	@Override
	public String getDescrizione() {
		if(!(this.hasAttrezzo(this.Illumina))) {
			return "Qui è buio!";
		}
		return super.getDescrizione();
	}
}
