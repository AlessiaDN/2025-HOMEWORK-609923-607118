package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Labirinto
 *
 * @author GiovanniPaoloBini (607118) e AlessiaDN (609923)
 * @see Labirinto
 * @version A
 */

class LabirintoTest {
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto();
		stanzaCorrente = labirinto.getStanzaCorrente(); // Attualmente l'atrio
	}

	/* TEST per getStanzaVincente */
	@Test
	public void testGetStanzaVincente_NotNull() {
		assertNotNull(labirinto.getStanzaVincente());
	}

	@Test
	public void testGetStanzaVincente_Nome() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}

	@Test
	public void testGetStanzaVincente_DifferenteDaStanzaCorrente() {
		assertNotSame(labirinto.getStanzaVincente(), labirinto.getStanzaCorrente());
	}

	/* TEST per getStanzaCorrente e setStanzaCorrente */
	@Test
	public void testGetStanzaCorrente_Inizializzazione() {
		assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
	}

	@Test
	public void testSetStanzaCorrente_ConNuovaStanza() {
		Stanza s = new Stanza("s");
		labirinto.setStanzaCorrente(s);
		assertSame(s, labirinto.getStanzaCorrente());
	}

	@Test
	public void testSetStanzaCorrente_Multiplo() {
		Stanza s1 = new Stanza("s1");
		Stanza s2 = new Stanza("s2");
		labirinto.setStanzaCorrente(s1);
		assertSame(s1, labirinto.getStanzaCorrente());
		labirinto.setStanzaCorrente(s2);
		assertSame(s2, labirinto.getStanzaCorrente());
	}

	/* TEST per creaStanze */

	@Test
	public void testCreaStanze_CoerenzaLabirinto() {
		Stanza aulaN11 = stanzaCorrente.getStanzaAdiacente(Direzione.est);
		assertNotNull(aulaN11);
		Stanza versoAtrio = aulaN11.getStanzaAdiacente(Direzione.ovest);
		assertNotNull(versoAtrio);
		assertEquals("Atrio", versoAtrio.getNome());
	}

	
	@Test
	public void testCreaStanze_AdiacenzeAtrio() {
		assertNotNull(stanzaCorrente.getStanzaAdiacente(Direzione.nord));
		assertNotNull(stanzaCorrente.getStanzaAdiacente(Direzione.est));
		assertNotNull(stanzaCorrente.getStanzaAdiacente(Direzione.sud));
		assertNotNull(stanzaCorrente.getStanzaAdiacente(Direzione.ovest));
	}


	@Test
	public void testCreaStanze_AdiacenzaEstAtrio() {
		Stanza est = stanzaCorrente.getStanzaAdiacente(Direzione.est);
		assertEquals("AulaN11", est.getNome());
	}
	
	@Test
	public void testCreaStanze_AdiacenzaBiblioteca() {
		Stanza biblioteca = labirinto.getStanzaVincente();
		Stanza sud = biblioteca.getStanzaAdiacente(Direzione.sud);
		assertNotNull(sud);
		assertEquals("Atrio", sud.getNome());
	}

	@Test
	public void testCreaStanze_AttrezzoInAtrio() {
		Attrezzo osso = stanzaCorrente.getAttrezzo("osso");
		assertNotNull(osso);
	}
	
	@Test
	public void testCreaStanze_AttrezzoInAulaN10() {
		Stanza aulaN10 = stanzaCorrente.getStanzaAdiacente(Direzione.sud);
		Attrezzo lanterna = aulaN10.getAttrezzo("lanterna");
		assertNotNull(lanterna);
	}
	
	/* Test per LabirintoNuilder */
	@Test
	public void testLabirintoBuilder_CreaLabirintoSemplice() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto.LabirintoBuilder builder = Labirinto.newBuilder("LabirintoPerTest.txt");
		Labirinto l = builder.getLabirinto();
		assertNotNull(l.getStanzaCorrente());
		assertNotNull(l.getStanzaVincente());
	}
	
	@Test
	public void testLabirintoBuilder_AttrezzoInStanza() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto lab = Labirinto.newBuilder("LabirintoPerTest.txt")
				.addStanzaIniziale("StanzaStart")
				.addAttrezzo("chiave", 1)
				.getLabirinto();
		assertNotNull(lab.getStanzaCorrente().getAttrezzo("chiave"));
	}
	
	@Test
	public void testLabirintoBuilder_AggiuntaStanze() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto lab = Labirinto.newBuilder("LabirintoPerTest.txt")
				.addStanza("Stanza1")
				.addStanza("Stanza2")
				.getLabirinto();
		assertNotNull(lab);
	}
}