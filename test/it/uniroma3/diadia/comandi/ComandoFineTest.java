package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class ComandoFineTest {

	 private ComandoFine comandoFine;
	 private Partita partita;
	 private IOSimulator io;

	 @BeforeEach
	 public void setUp() {
		 comandoFine = new ComandoFine();
		 partita = new Partita();
		 io = new IOSimulator(new String[0]);
		 comandoFine.setIO(io);
	 }
	 
	 /* Test per setParametro */
	 @Test
	 public void testSetParametro_NonFaNiente() {
		 comandoFine.setParametro("qualunque");
		 assertNull(comandoFine.getParametro());
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
	    public void testEsegui_MostraMessaggioUscita() {
	        comandoFine.esegui(partita);
	        assertTrue(io.contieneMessaggio("Grazie di aver giocato!"));
	    }
}
