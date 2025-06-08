package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe testa tutti i metodi della classe Borsa
 *
 * @author GiovanniPaoloBini (607118) e AlessiaDN (609923)
 * @see Borsa
 * @version A
 */

class BorsaTest {
    private Borsa borsa;
    private Attrezzo osso;

    @BeforeEach
    public void setUp() {
        borsa = new Borsa();
        osso = new Attrezzo("osso", 1);
    }
    /* TEST per toString */
	@Test
	public void testToString_BorsaVuota() {
		assertEquals("Borsa vuota", borsa.toString());
	}

	@Test
	public void testToString_ConUnAttrezzo() {
		borsa.addAttrezzo(osso);
		String descrizione = borsa.toString();
		assertTrue(descrizione.contains("Contenuto borsa (1kg/10kg): [osso (1kg)]"));
	}

	@Test
	public void testToString_ConDueAttrezziUguali() {
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(osso);
		String descrizione = borsa.toString();
		assertTrue(descrizione.contains("Contenuto borsa (1kg/10kg): [osso (1kg)]"));
	}


    /* TEST per addAttrezzo */
    @Test
    public void testAddAttrezzo_SingoloAumentaPeso() {
        assertTrue(borsa.addAttrezzo(osso));
        assertEquals(1, borsa.getPeso());
    }

    @Test
    public void testAddAttrezzo_PesoEccessivoNonAggiunge() {
        Attrezzo incudine = new Attrezzo("incudine", 15);
        assertFalse(borsa.addAttrezzo(incudine));
        assertTrue(borsa.isEmpty());
    }
    
    @Test
    public void testAddAttrezzo_BorsaPienaNonAggiunge() {
    	for (int i = 0; i < 10; i++) {
            borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
    	assertFalse(borsa.addAttrezzo(new Attrezzo("attrezzo10", 1)));
    }
    
    @Test
    public void testAddAttrezzo_BorsaConPesoMassimoRaggiuntoNonAggiunge() {
        Attrezzo incudine = new Attrezzo("incudine", 10);
        borsa.addAttrezzo(incudine);
        assertFalse(borsa.addAttrezzo(osso));
    }

    /* TEST per getAttrezzo */
    @Test
    public void testGetAttrezzo_RestituisceAttrezzoAggiunto() {
        borsa.addAttrezzo(osso);
        assertEquals(osso, borsa.getAttrezzo("osso"));
    }

    @Test
    public void testGetAttrezzo_ConNomeNonPresenteRestituisceNull() {
        assertNull(borsa.getAttrezzo("inesistente"));
    }

    @Test
    public void testGetAttrezzo_ConNomeNullRestituisceNull() {
        assertNull(borsa.getAttrezzo(null));
    }

    /* TEST per getPeso */
    @Test
    public void testGetPeso_RestituisceZeroSeBorsaVuota() {
        assertEquals(0, borsa.getPeso());
    }

    @Test
    public void testGetPeso_AumentaDopoAggiuntaAttrezzo() {
        borsa.addAttrezzo(osso);
        assertEquals(1, borsa.getPeso());
    }

    @Test
    public void testGetPeso_DiminuisceDopoRimozioneAttrezzo() {
        borsa.addAttrezzo(osso);
        assertEquals(1, borsa.getPeso());
        borsa.removeAttrezzo(osso.getNome());
        assertEquals(0, borsa.getPeso());
    }
    
    /* TEST per isEmpty */
    @Test
    public void testIsEmpty_RestituisceTrueSeBorsaVuota() {
        assertTrue(borsa.isEmpty());
    }

    @Test
    public void testIsEmpty_RestituisceFalseSeBorsaNonVuota() {
        borsa.addAttrezzo(osso);
        assertFalse(borsa.isEmpty());
    }

    @Test
    public void testIsEmpty_DopoRimozioneAttrezzo() {
        borsa.addAttrezzo(osso);
        borsa.removeAttrezzo(osso.getNome());
        assertTrue(borsa.isEmpty());
    }
    
    /* TEST per hasAttrezzo */
    @Test
    public void testHasAttrezzo_RestituisceTrueSePresente() {
        borsa.addAttrezzo(osso);
        assertTrue(borsa.hasAttrezzo("osso"));
    }

    @Test
    public void testHasAttrezzo_RestituisceFalseSeNonPresente() {
        assertFalse(borsa.hasAttrezzo("inesistente"));
    }

    @Test
    public void testHasAttrezzo_ConNomeNullRestituisceFalse() {
        assertFalse(borsa.hasAttrezzo(null));
    }
    
    /* TEST per removeAttrezzo */
    @Test
    public void testRemoveAttrezzo_RimossoNonPiùPresente() {
        borsa.addAttrezzo(osso);
        Attrezzo rimosso = borsa.removeAttrezzo(osso.getNome());
        
        assertSame(osso, rimosso);
        assertNull(borsa.getAttrezzo("osso"));
    }


    @Test
    public void testRemoveAttrezzo_AggiornaPesoBorsa() {
        borsa.addAttrezzo(osso);
        int pesoIniziale = borsa.getPeso();
        borsa.removeAttrezzo(osso.getNome());
        assertEquals(pesoIniziale - osso.getPeso(), borsa.getPeso());
    }

    @Test
    public void testRemoveAttrezzo_DaBorsaVuota() {
        assertNull(borsa.removeAttrezzo(osso.getNome()));
    }
}