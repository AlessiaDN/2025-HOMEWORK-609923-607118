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

class ComandoFineTest {

	 private ComandoFine comandoFine;
	 private Partita partita;
	 private IOSimulator io;

	 @BeforeEach
	    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
	        comandoFine = new ComandoFine();
	        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
	        io = new IOSimulator(Arrays.asList());
	        comandoFine.setIO(io);
	    }
	 
	 /* Test per setParametro */
	 @Test
	 public void testSetParametro_Uguale() {
	        comandoFine.setParametro("qualunque");
	        assertEquals("qualunque", comandoFine.getParametro());
	 }	 
	 
	 /* Test per get */
	 @Test
	 public void testGetNome() {
		 assertEquals("fine", comandoFine.getNome());
	 }

	 @Test
	 public void testGetParametro() {
	        assertNull(comandoFine.getParametro());
	 }
	 
	 /* TEST per esegui */
	 
	 @Test
	 public void testEsegui_ImpostaPartitaFinita() {
		 	comandoFine.esegui(partita);
	        assertTrue(partita.isFinita());
	 }
	 
	 @Test
	 public void testEsegui_NonModificaAltriStatiPartita() {
	        int cfuIniziali = partita.getGiocatore().getCfu();
	        comandoFine.esegui(partita);
	        assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
	        assertNotNull(partita.getStanzaCorrente());
	  }
	 
	  @Test
	  public void testEsegui_MostraMessaggioUscita() {
		  	comandoFine.esegui(partita);
	        assertTrue(io.contieneMessaggio("Grazie di aver giocato!"));
	  }
}
