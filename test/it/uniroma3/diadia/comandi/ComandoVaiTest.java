package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

/* Questa classe testa tutti i metodi della classe ComandoVai */

class ComandoVaiTest {

    private Partita partita;
    private ComandoVai comandoVai;
    private Stanza atrio;
    private Stanza biblioteca;
    private IOSimulator io;
    
    @BeforeEach
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        io = new IOSimulator(Arrays.asList());
        partita = new Partita(Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto());
        comandoVai = new ComandoVai();
        comandoVai.setIO(io);
        
        atrio = new Stanza("Atrio");
        biblioteca = new Stanza("Biblioteca");
        
        atrio.impostaStanzaAdiacente(Direzione.nord, biblioteca);
        biblioteca.impostaStanzaAdiacente(Direzione.sud, atrio);
        
        partita.setStanzaCorrente(atrio);
    }
    
    /* Test per setParametro */
    @Test
    public void testSetParametro() {
        comandoVai.setParametro("nord");
        assertEquals("nord", comandoVai.getParametro());
    }
    
    /* Test per get */
    @Test
    public void testGetNome() {
        assertEquals("vai", comandoVai.getNome());
    }
    
    /* Test per esegui */
    @Test
    public void testEsegui_DirezioneValida() {
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
        assertEquals(19, partita.getGiocatore().getCfu());
    }
    
    @Test
    public void testEsegui_ConParametroVuoto() {
        comandoVai.setParametro("");
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
    }
    
    @Test
    public void testEsegui_DirezioneInesistente() {
        comandoVai.setParametro("est");
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
        assertEquals(20, partita.getGiocatore().getCfu());
    }
}