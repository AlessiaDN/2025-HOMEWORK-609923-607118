package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IOSimulatorPartiteTest {

    @Test
    public void testPercorsoVittoriaDiretto() {
        String[] comandi = {"vai sud", "prendi lanterna", "vai nord", "vai ovest", 
        					"posa lanterna", "prendi chiave", "vai est",
        					"posa chiave", "vai nord"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Hai vinto"));
    }
    
    @Test
    public void testRaccoltaTuttiAttrezzi() {
        String[] comandi = {"prendi osso", "vai sud", "prendi lanterna", 
                           "vai ovest", "prendi chiave", "fine"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("osso aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("lanterna aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("chiave aggiunto all'inventario"));
    }
    
    @Test
    public void testCompletoConAiuto() {
        String[] comandi = {"aiuto", "vai sud", "prendi lanterna", 
                           "guarda", "vai ovest", "fine"};
        
        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("vai"));
        assertTrue(io.contieneMessaggio("prendi"));
        assertTrue(io.contieneMessaggio("Laboratorio Campus"));
    }
    
}
