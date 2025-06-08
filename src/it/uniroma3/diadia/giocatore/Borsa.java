package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Questa classe modella le interazioni con la borsa
 * e la sua capienza massima
 *
 * @author  docente di POO
 * @author Modificato da GiovanniPaoloBini (607118) e AlessiaDN (609923)
 * @see Stanza
 * @see Attrezzo
 * @version A
 */

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();
	
	private Map<String, Attrezzo> attrezzi;
	private int pesoAttuale;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Crea una nuova Borsa impostandone il peso massimo
	 * e con un massimo di 10 attrezzi.
	 * @param pesoMax
	 */
	public Borsa(int pesoMax) {
		this.attrezzi = new TreeMap<>();
		this.pesoMax = pesoMax;
		this.pesoAttuale = 0;
	}
	
	/**
	 * Aggiunge un attrezzo alla Borsa.
	 * @param attrezzo
	 * @return true se l'attrezzo è stato aggiunto e 
	 * quindi la Borsa non è piena, falso altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		 if (!this.attrezzi.containsKey(attrezzo.getNome())) {
		        this.attrezzi.put(attrezzo.getNome(), attrezzo);
		        this.pesoAttuale += attrezzo.getPeso();
		        return true;
		    }
		return false;
	}
	
	/**
	 * Verifica il peso massimo che la borsa può supportare.
	 * @return valore intero del peso massimo
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Prende un attrezzo dalla Borsa.
	 * @param nomeAttrezzo
	 * @return il riferimento all'attrezzo se trovato, null altrimenti
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo != null && this.attrezzi.containsKey(nomeAttrezzo))
			a = this.attrezzi.get(nomeAttrezzo);
		return a;
	}
	
	/**
	 * Verifica il peso corrente della Borsa.
	 * @return valore intero del peso della Borsa
	 */
	public int getPeso() {
		return this.pesoAttuale;
	}
	
	/**
	 * Verifica se la Borsa è vuota.
	 * @return true se vuota, falso altrimenti
	 */
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}
	
	/**
	 * Verifica se un attrezzo è presente nella Borsa.
	 * @param nomeAttrezzo
	 * @return true se presente, falso altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove un attrezzo dalla Borsa (ricerca in base all'indirizzo).
	 * @param attrezzo
	 * @return riferimento all'attrezzo appena rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo!=null){
			a = this.attrezzi.remove(nomeAttrezzo);
			if(a!=null) {
				this.pesoAttuale -= a.getPeso();
			}
		}
		return a;
	}
	
	/**
	 * Restituisce una rappresentazione stringa degli elementi 
	 * nella borsa con relativo peso
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append(this.attrezzi.values());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public String getContenutoOrdinato() {
		StringBuilder s = new StringBuilder();

		if(!this.isEmpty()) {
			s.append("[Contenuto Borsa]");
			s.append("\nOrdinamento per peso    " + getContenutoOrdinatoPerPeso());
			s.append("\nOrdinamento per nome    " + getContenutoOrdinatoPerNome());
			s.append("\nRaggruppamento per peso " + getContenutoRaggruppatoPerPeso());
		}
		else {
			s.append("Borsa vuota");
		}
	    
	    return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista = new ArrayList<>();
		
		lista.addAll(this.attrezzi.values());
		Collections.sort(lista, new ComparatorePerPeso());
		
		return lista;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> map = new TreeMap<>();
		
		for(Attrezzo a : this.attrezzi.values()){
			if(map.containsKey(a.getPeso())) {
				map.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo> s = new HashSet<Attrezzo>();
				s.add(a);
				map.put(a.getPeso(), s);
			}
		}
		return map;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> s = new TreeSet<>(new ComparatorePerPeso());
		s.addAll(this.attrezzi.values());
		return s;
	}
}