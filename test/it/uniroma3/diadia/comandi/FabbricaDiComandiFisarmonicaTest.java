package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;

/* Questa classe testa tutti i metodi della classe FabbricaDiComandiFisarmonica */

class FabbricaDiComandiFisarmonicaTest {

    private FabbricaDiComandiFisarmonica fabbrica;
    private IOSimulator io;

    @BeforeEach
    public void setUp() {
        io = new IOSimulator(new String[0]);
        fabbrica = new FabbricaDiComandiFisarmonica(io);
    }
    
    /* Test per costriusciComando */
    @Test
    public void testCostruisciComando_Prendi() {
        Comando comando = fabbrica.costruisciComando("prendi osso");
        assertEquals("prendi", comando.getNome());
        assertEquals("osso", comando.getParametro());
    }
    
    @Test
    public void testCostruisciComando_Posa() {
        Comando comando = fabbrica.costruisciComando("posa lanterna");
        assertEquals("posa", comando.getNome());
        assertEquals("lanterna", comando.getParametro());
    }
    
    @Test
    public void testCostruisciComando_Vai() {
        Comando comando = fabbrica.costruisciComando("vai nord");
        assertEquals("vai", comando.getNome());
        assertEquals("nord", comando.getParametro());
    }

    
}