package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;

/* Questa classe testa tutti i metodi della classe FabbricaDiComandiFisarmonica */

class FabbricaDiComandiFisarmonicaTest {

    private FabbricaDiComandiFisarmonica fabbrica;
    private IOSimulator io;

    @BeforeEach
    public void setUp() {
        io = new IOSimulator(Arrays.asList());
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
    
    @Test
    public void testCostruisciComando_Guarda() {
        Comando comando = fabbrica.costruisciComando("guarda");
        assertEquals("guarda", comando.getNome());
        assertNull(comando.getParametro());
    }
    
    @Test
    public void testCostruisciComando_Aiuto() {
        Comando comando = fabbrica.costruisciComando("aiuto");
        assertEquals("aiuto", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    public void testCostruisciComando_Fine() {
        Comando comando = fabbrica.costruisciComando("fine");
        assertEquals("fine", comando.getNome());
        assertNull(comando.getParametro());
    }
    
}