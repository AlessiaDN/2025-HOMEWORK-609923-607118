package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class ComandoAiutoTest {
	
	private ComandoAiuto comandoAiuto;
	private Partita partita;
    private IOSimulator io;
	
	@BeforeEach
	public void setUp() {
		comandoAiuto = new ComandoAiuto();
	    partita = new Partita();
	    io = new IOSimulator(new String[0]);
	    comandoAiuto.setIO(io);
	}

	/* Test per setParametro */
    @Test
    public void testSetParametro_NonFaNiente() {
        comandoAiuto.setParametro("qualunque");
        assertNull(comandoAiuto.getParametro());
    }
    
    /* Test per get */
    @Test
    public void testGetNome() {
        assertEquals("aiuto", comandoAiuto.getNome());
    }

    @Test
    public void testGetParametro() {
        assertNull(comandoAiuto.getParametro());
    } 
}
