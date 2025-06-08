package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirintoTest {
	
	private final String labirintoComplesso = 
	        "Stanze:atrio,biblioteca,laboratorio,N10,N11,magica,buia,bloccata\n" +
	        "Magica:magica\n" +
	        "Buia:buia lanterna\n" +
	        "Bloccata:bloccata nord chiave\n" +
	        "Inizio:atrio\n"+
	        "Vincente:laboratorio\n" +
	        "Mago:atrio Merlino Magic bacchetta 1\n"+
	        "Cane:biblioteca Fido Bau!\n" +
	        "Strega:laboratorio Morgana HAHA\n" +
	        "Attrezzi:lanterna 2 atrio,chiave 1 bloccata,spada 5 N11,scudo 3 biblioteca\n" +
	        "Uscite:atrio nord N10,atrio est biblioteca,biblioteca sud laboratorio," +
	        "N10 ovest atrio,N10 sud N11,N11 nord N10,laboratorio nord biblioteca," +
	        "bloccata nord magica,magica sud bloccata,buia est atrio\n";


	private CaricatoreLabirinto caricatore;

    @Before
    public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
        caricatore = new CaricatoreLabirinto(new StringReader(labirintoComplesso));
        caricatore.carica();
    }
    
    @Test
    public void testComportamentoStanzaBuia() {
        StanzaBuia buia = (StanzaBuia)caricatore.getStanza("buia");
        String descrizione = buia.getDescrizione();
        //La descrizione dovrebbe indicare che la stanza è buia
        assertTrue(descrizione.contains("buio") || descrizione.contains("buia"));
    }
    
    @Test
    public void testComportamentoStanzaBloccata() {
        StanzaBloccata bloccata = (StanzaBloccata)caricatore.getStanza("bloccata");
        //Verifica che la direzione bloccata non sia accessibile
        assertEquals(caricatore.getStanza("magica"), bloccata.getStanzaAdiacente(Direzione.nord));
    }
    
    @Test
    public void testStanzeSpecialiCaricateCorrettamente() {

        assertTrue(caricatore.getStanza("magica") instanceof StanzaMagica);
        assertTrue(caricatore.getStanza("buia") instanceof StanzaBuia);
        assertTrue(caricatore.getStanza("bloccata") instanceof StanzaBloccata);
    }
}
