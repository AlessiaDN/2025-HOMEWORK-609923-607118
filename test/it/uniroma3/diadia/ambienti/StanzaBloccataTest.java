package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacente;
	private Attrezzo attrSbloccante;
	
	@BeforeEach
    public void setUp() {
        stanzaBloccata = new StanzaBloccata("StanzaBloccata", Direzione.nord, "chiave");
        stanzaAdiacente = new Stanza("StanzaAdiacente");
        stanzaBloccata.impostaStanzaAdiacente(Direzione.nord, stanzaAdiacente);
        attrSbloccante = new Attrezzo("chiave", 1);
    }
	
	/* Test per getDescrizione */
	
	@Test
	public void testGetDescrizioneConAttrezzo() {
		stanzaBloccata.addAttrezzo(attrSbloccante);
		String descrizione = stanzaBloccata.getDescrizione();
		assertTrue(descrizione.contains("sbloccata"));
		assertTrue(descrizione.contains("chiave"));
	}
	
	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		String descrizione = stanzaBloccata.getDescrizione();
		assertTrue(descrizione.contains("bloccata"));
		assertTrue(descrizione.contains("chiave"));
	}
	
	/* Test per getStanzaAdiacente */
	
	@Test
    public void testGetStanzaAdiacente_ConDirezioneDiversa() {
        stanzaBloccata.impostaStanzaAdiacente(Direzione.est, stanzaAdiacente);
        Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.est);
        assertEquals(stanzaAdiacente, risultato);
    }
	
	@Test
	public void testGetStanzaAdiacente_DirezioneBloccataSenzaAttrezzo() {
	    Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.nord);
	    assertEquals(stanzaBloccata, risultato);
	}
	
	@Test
	   public void testGetStanzaAdiacente_ConAttrezzoSbagliato() {
	       stanzaBloccata.addAttrezzo(new Attrezzo("pala", 1));
	       Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.nord);
	       assertEquals(stanzaBloccata, risultato);
	   }
	 
	@Test
    public void testGetStanzaAdiacente_DirezioneNonBloccataNull() {
        Stanza risultato = stanzaBloccata.getStanzaAdiacente(Direzione.sud);
        assertNull(risultato); // Non c'è stanza adiacente a sud
    }
	
}
