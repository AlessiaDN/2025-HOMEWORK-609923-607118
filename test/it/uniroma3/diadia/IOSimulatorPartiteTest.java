package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;


class IOSimulatorPartiteTest {
	private Labirinto labirinto;
	
	@BeforeEach
	public void setup() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = Labirinto.newBuilder("LabirintoPerTest.txt").getLabirinto();
	}

    @Test
    public void testPercorsoVittoriaDiretto() throws Exception{
        List<String> listaComandi = Arrays.asList("vai sud", "prendi lanterna", "vai nord", "vai ovest", 
        					"posa lanterna", "prendi chiave", "vai est",
        					"posa chiave", "vai nord");
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Hai vinto!"));
    }
    
    @Test
    public void testRaccoltaTuttiAttrezzi() throws Exception{
        List<String> listaComandi = Arrays.asList("prendi osso", "vai sud", "prendi lanterna", 
                           "vai ovest", "prendi chiave", "fine");
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("osso aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("lanterna aggiunto all'inventario"));
        assertTrue(io.contieneMessaggio("chiave aggiunto all'inventario"));
    }
    
    @Test
    public void testEsaurimentoCfu() throws Exception {
    	List<String> listaComandi = new ArrayList<>();
    	for (int i = 0; i < 21; i++) {
    	    listaComandi.add("vai est");
    	}
    	listaComandi.add("fine");
        
        IOSimulator io = new IOSimulator(listaComandi);
        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();
        
        assertTrue(io.contieneMessaggio("Hai esaurito i CFU"));
    }    
}
