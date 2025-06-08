package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/* Questa classe testa tutti i metodi della classe ComandoPrendi */

class ComandoPrendiTest {

	 private ComandoPrendi comandoPrendi;
	 private Partita partita;
	 private IOSimulator io;
	 private Stanza stanza;
	 private Attrezzo attrezzoTest;

	 @BeforeEach
	    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
	        comandoPrendi = new ComandoPrendi();
	        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
	        io = new IOSimulator(Arrays.asList());
	        comandoPrendi.setIO(io);
	        
	        stanza = new Stanza("Stanza Test");
	        partita.setStanzaCorrente(stanza);
	        
	        attrezzoTest = new Attrezzo("osso", 1);
	        stanza.addAttrezzo(attrezzoTest);
	    }
	 
	 /* Test per setParametro */
	 @Test
	 public void testSetParametro() {
		 comandoPrendi.setParametro("spada");
		 assertEquals("spada", comandoPrendi.getParametro());
	 }
	 
	 /* Test per get */
	 @Test
	 public void testGetNome() {
		 assertEquals("prendi", comandoPrendi.getNome());
	 }

	 @Test
	 public void testGetParametro() {
		 comandoPrendi.setParametro("osso");
		 assertEquals("osso", comandoPrendi.getParametro());
	 }
	 
	 /* Test per esegui */
	 @Test
	    public void testEsegui_ConStanzaVuota() {
	        stanza.removeAttrezzo(attrezzoTest);
	        comandoPrendi.setParametro("osso");
	        comandoPrendi.esegui(partita);
	        
	        assertTrue(io.contieneMessaggio("La stanza non ha attrezzi"));
	    }
	 
	 @Test
	 public void testEsegui_AttrezzoPresente() {
		 comandoPrendi.setParametro("osso");
		 comandoPrendi.esegui(partita);
		 
		 assertTrue(io.contieneMessaggio("osso aggiunto all'inventario"));
		 assertFalse(stanza.hasAttrezzo("osso"));
		 assertTrue(partita.getGiocatoreBorsa().hasAttrezzo("osso"));
	 }
	 
	 @Test
	 public void testEsegui_SenzaSpecificareAttrezzo() {
		 comandoPrendi.setParametro(null);
		 comandoPrendi.esegui(partita);
	        
		 assertTrue(io.contieneMessaggio("Nessun attrezzo inserito"));
	 }
}