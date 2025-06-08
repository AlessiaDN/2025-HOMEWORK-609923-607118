package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

//Comparatore del peso per oggetti di tipo Attrezzo
public class ComparatorePerPeso implements Comparator<Attrezzo> {
	public int compare(Attrezzo attr1, Attrezzo attr2) {
		if(attr1.getPeso()-attr2.getPeso() == 0)
			return attr1.getNome().compareTo(attr2.getNome());
		return attr1.getPeso()-attr2.getPeso();
	}
}
