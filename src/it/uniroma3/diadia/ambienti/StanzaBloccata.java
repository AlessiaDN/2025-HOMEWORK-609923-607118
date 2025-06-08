package it.uniroma3.diadia.ambienti;

/* Estensione della classe Stanza che permette di creare una stanza con alcune direzioni bloccate */
/* Da sbloccare con un apposito attrezzo */

public class StanzaBloccata extends Stanza{
	Direzione dirBloccata;
	String attSbloccatore;
	
	public StanzaBloccata(String nome, Direzione dirBloccata, String attSbloccatore) {
		super(nome);
		this.attSbloccatore = attSbloccatore;
		this.dirBloccata = dirBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(direzione.equals(this.dirBloccata) && !(this.hasAttrezzo(this.attSbloccatore))) {
			return this;
		}
		else {
			return super.getStanzaAdiacente(direzione);
		}
	}
	
	@Override
	public String getDescrizione() {
		if(!(this.hasAttrezzo(this.attSbloccatore))) {
			return super.getDescrizione()+"\nLa direzione" + dirBloccata + "è bloccata e hai bisogno di" + attSbloccatore + "per sbloccarla\n";
		}
		else {
			return super.getDescrizione()+"\nLa direzione" + dirBloccata + "è stata sbloccata grazie a " + attSbloccatore;
		}
	}
}