package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoGuardaTest {

	private ComandoGuarda comandoGuarda;
    private Partita partita;
    private IOSimulator io;

    @BeforeEach
    public void setUp() {
        comandoGuarda = new ComandoGuarda();
        partita = new Partita();
        io = new IOSimulator(new String[0]);
        comandoGuarda.setIO(io);
    }
    
    /* Test per setParametro */
    @Test
    public void testSetParametro_NonHaEffetti() {
        comandoGuarda.setParametro("qualunque");
        assertNull(comandoGuarda.getParametro());
    }
    
    /* Test per get */
    @Test
    public void testGetNome() {
        assertEquals("guarda", comandoGuarda.getNome());
    }

    @Test
    public void testGetParametro() {
        assertNull(comandoGuarda.getParametro());
    }
    
    /* Test per esegui */
    @Test
    public void testEsegui_MostraDescrizioneStanza() {
        Stanza stanza = new Stanza("Stanza di Test");
        stanza.addAttrezzo(new Attrezzo("osso", 1));
        partita.setStanzaCorrente(stanza);       
        comandoGuarda.esegui(partita);
        
        assertTrue(io.contieneMessaggio("Stanza di Test"));
        assertTrue(io.contieneMessaggio("osso"));
    }
    
    @Test
    public void testEsegui_MostraContenutoBorsa() {
        Borsa borsa = partita.getGiocatoreBorsa();
        borsa.addAttrezzo(new Attrezzo("lanterna", 3));
        comandoGuarda.esegui(partita);
        
        assertTrue(io.contieneMessaggio("lanterna"));
        assertTrue(io.contieneMessaggio("3kg"));
    }

    @Test
    public void testEsegui_ConBorsaVuota() {
        comandoGuarda.esegui(partita);       
        assertTrue(io.contieneMessaggio("Borsa vuota"));
    }
    
}
