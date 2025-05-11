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
		stanzaBloccata = new StanzaBloccata("StanzaBloccata", "nord", "chiave");
		stanzaAdiacente = new Stanza("StanzaAdiacente");
		stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacente);
		attrSbloccante = new Attrezzo("chiave", 1);;
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
	public void testGetStanzaAdiacenteDirBloccataConAttrezzo() {
		stanzaBloccata.addAttrezzo(attrSbloccante);
		Stanza result = stanzaBloccata.getStanzaAdiacente("nord");
		assertEquals(stanzaAdiacente, result);
	}
	
	@Test
	public void testStanzaAdiacenteDirBloccataSenzaAttrezzo() {
		Stanza result = stanzaBloccata.getStanzaAdiacente("nord");
		assertEquals(stanzaBloccata, result);
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneDiversa() {
		stanzaBloccata.impostaStanzaAdiacente("est", stanzaAdiacente);
		Stanza result = stanzaBloccata.getStanzaAdiacente("est");
		assertEquals(stanzaAdiacente, result);
	}
	
	@Test
	public void testGetStanzaAdiacenteAttrezzoSbagliato() {
		 stanzaBloccata.addAttrezzo(new Attrezzo("pala", 1));
	     Stanza result = stanzaBloccata.getStanzaAdiacente("nord");
	     assertEquals(stanzaBloccata, result);
	}
	
	@Test
	 public void testGetStanzaAdiacenteDirezioneNonBloccataNull() {
        Stanza risultato = stanzaBloccata.getStanzaAdiacente("sud");
        assertNull(risultato);
    }
	
	
}
