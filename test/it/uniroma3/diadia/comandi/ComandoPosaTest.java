package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/* Questa classe testa tutti i metodi della classe ComandoPosa */

class ComandoPosaTest {

	private ComandoPosa comandoPosa;
    private Partita partita;
    private IOSimulator io;
    private Stanza stanza;
    private Attrezzo attrezzoValido;

	@BeforeEach
    public void setUp() {
        comandoPosa = new ComandoPosa();
	    partita = new Partita();
	    io = new IOSimulator(new String[0]);
        comandoPosa.setIO(io);
	        
        stanza = new Stanza("Stanza Test");
	    partita.setStanzaCorrente(stanza);
	        
        attrezzoValido = new Attrezzo("martello", 2);
        partita.getGiocatoreBorsa().addAttrezzo(attrezzoValido);
	}
	
	/* Test per setParametro */
    @Test
    public void testSetParametro() {
        comandoPosa.setParametro("lanterna");
        assertEquals("lanterna", comandoPosa.getParametro());
    }
    
    /* TEST per get */
    @Test
    public void testGetNome() {
        assertEquals("posa", comandoPosa.getNome());
    }
    
    /* Test per esegui */
    @Test
    public void testEsegui_AttrezzoValido() {
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("martello aggiunto alla stanza"));
        assertFalse(partita.getGiocatoreBorsa().hasAttrezzo("martello"));
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
    }
    
    @Test
    public void testEsegui_AttrezzoConStessoNomeInStanza() {
        partita.getStanzaCorrente().addAttrezzo(new Attrezzo("martello", 3));
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("martello aggiunto alla stanza"));
        assertEquals("martello", partita.getStanzaCorrente().getAttrezzi()[0].getNome());
        assertEquals("martello", partita.getStanzaCorrente().getAttrezzi()[1].getNome());
    }
    
    @Test
    public void testEsegui_ConBorsaVuota() {
        partita.getGiocatoreBorsa().removeAttrezzo(attrezzoValido);
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("La borsa non ha attrezzi"));
    }   
}