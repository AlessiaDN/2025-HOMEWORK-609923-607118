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

/* Questa classe testa tutti i metodi della classe ComandoPosa */

class ComandoPosaTest {

	private ComandoPosa comandoPosa;
    private Partita partita;
    private IOSimulator io;
    private Stanza stanza;
    private Attrezzo attrezzoValido;

    @BeforeEach
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        comandoPosa = new ComandoPosa();
        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        io = new IOSimulator(Arrays.asList());
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
        
        assertFalse(io.contieneMessaggio("martello aggiunto alla stanza"));
        assertEquals("martello (3kg)", partita.getStanzaCorrente().getAttrezzo("martello").toString());
    }
    
    @Test
    public void testEsegui_ConBorsaVuota() {
        partita.getGiocatoreBorsa().removeAttrezzo(attrezzoValido.getNome());
        comandoPosa.setParametro("martello");
        comandoPosa.esegui(partita);
        
        assertTrue(io.contieneMessaggio("La borsa non ha attrezzi"));
    }
}