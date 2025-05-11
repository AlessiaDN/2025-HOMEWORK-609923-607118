package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private StanzaMagica stanzaMagica;
    private Attrezzo attrezzoNormale;
    private Attrezzo attrezzoMagico;

    @BeforeEach
    public void setUp() {
    	stanzaMagica = new StanzaMagica("Stanza Magica");
    	attrezzoNormale = new Attrezzo("spada", 3);
    	attrezzoMagico = new Attrezzo("pala", 2);
    }

	@Test
	public void testMoltiAttrezziMagici() {
		stanzaMagica.addAttrezzo(new Attrezzo("osso", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("chiave", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("lanterna", 2));
		
		assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("scudo", 2)));
		assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("martello", 3)));
		
		assertNotNull(stanzaMagica.getAttrezzo("olletram"));
	    assertEquals(6, stanzaMagica.getAttrezzo("olletram").getPeso());
	    assertNotNull(stanzaMagica.getAttrezzo("oducs"));
	    assertEquals(4, stanzaMagica.getAttrezzo("oducs").getPeso());
	}
	
	@Test
	public void testSogliaPersonalizzata() {
		 StanzaMagica stanzaCustom = new StanzaMagica("Custom", 1);
		 
		  assertTrue(stanzaCustom.addAttrezzo(attrezzoNormale));
	      assertEquals("spada", stanzaCustom.getAttrezzo("spada").getNome());
	      
	      assertTrue(stanzaCustom.addAttrezzo(attrezzoMagico));
	      assertNull(stanzaCustom.getAttrezzo("pala"));
	      assertNotNull(stanzaCustom.getAttrezzo("alap"));
	      assertEquals(4, stanzaCustom.getAttrezzo("alap").getPeso());
	}
	
	@Test
	public void testAttrezziMagiciDopoSoglia() {
		stanzaMagica.addAttrezzo(new Attrezzo("chiave", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("osso", 1));
		stanzaMagica.addAttrezzo(new Attrezzo("lanterna", 2));
		
		assertTrue(stanzaMagica.addAttrezzo(attrezzoMagico));
		
		 assertNull(stanzaMagica.getAttrezzo("pala"));
	     Attrezzo trasformato = stanzaMagica.getAttrezzo("alap");
	     assertNotNull(trasformato);
	     assertEquals("alap", trasformato.getNome());
	     assertEquals(4, trasformato.getPeso()); 
	}
	
	@Test
	public void testAttrezziPrimaDellaSoglia() {
		assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("chiave", 1)));
		assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("osso", 1)));
        assertTrue(stanzaMagica.addAttrezzo(new Attrezzo("lanterna", 2)));
        
        assertEquals("osso", stanzaMagica.getAttrezzo("osso").getNome());
        assertEquals(1, stanzaMagica.getAttrezzo("osso").getPeso());
        assertEquals("lanterna", stanzaMagica.getAttrezzo("lanterna").getNome());
        assertEquals(2, stanzaMagica.getAttrezzo("lanterna").getPeso());
	}
}
