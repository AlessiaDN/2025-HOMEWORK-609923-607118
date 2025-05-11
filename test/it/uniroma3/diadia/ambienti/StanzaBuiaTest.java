package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private Attrezzo altroAttrezzo;
	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;
	
	@BeforeEach
	public void setUp() {
		stanzaBuia = new StanzaBuia("Stanza Buia", "lanterna");
		altroAttrezzo = new Attrezzo("osso", 1);
		lanterna = new Attrezzo("lanterna", 1);
	}

	/* Test per getDescrizione */
	
	@Test
	public void testGetDescrizioneConAttrezzo() {
		stanzaBuia.addAttrezzo(lanterna);
		assertNotEquals("Qui è buio!", stanzaBuia.getDescrizione());
		assertTrue(stanzaBuia.getDescrizione().contains("Stanza Buia"));
	}
	
	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		assertEquals("Qui è buio!", stanzaBuia.getDescrizione());	
	}
	
	@Test
	public void testGetDescrizioneConAttrezzoIlluminanteEAltroAttrezzo() {
		stanzaBuia.addAttrezzo(altroAttrezzo);
		stanzaBuia.addAttrezzo(lanterna);
		assertNotEquals("Qui è buio!", stanzaBuia.getDescrizione());
		assertTrue(stanzaBuia.getDescrizione().contains("Stanza Buia"));
		assertTrue(stanzaBuia.getDescrizione().contains("lanterna"));
		assertTrue(stanzaBuia.getDescrizione().contains("osso"));
	}
	
	@Test
	public void testGetDescrizioneConAltroAttrezzo() {
		stanzaBuia.addAttrezzo(altroAttrezzo);
		assertEquals("Qui è buio!", stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneDopoCheAttrezzoIlluminanteRimosso() {
		stanzaBuia.addAttrezzo(lanterna);
		assertNotEquals("Qui è buio!", stanzaBuia.getDescrizione());
		stanzaBuia.removeAttrezzo(lanterna);
		assertEquals("Qui è buio!", stanzaBuia.getDescrizione());
		
	}
	
}
