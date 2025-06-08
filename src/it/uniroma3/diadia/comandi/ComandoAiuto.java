package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;
import java.net.URL;
import java.net.URLDecoder;
import java.util.jar.*;

/* Classe che gestisce il comando aiuto */

public class ComandoAiuto extends AbstractComando {
	
	private static final String PACKAGE = "it.uniroma3.diadia.comandi";
	
	@Override
	public void esegui (Partita partita) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = PACKAGE.replace('.', '/');
		URL resource = classLoader.getResource(path);
		
		if (resource == null) {
			throw new RuntimeException("Package non trovato: " + PACKAGE);
		}
		
		if (resource.getProtocol().equals("jar")) {
			try {
				String rawJarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
				String jarPath = URLDecoder.decode(rawJarPath, "UTF-8");
                JarFile jar = new JarFile(jarPath);
                
                Enumeration<JarEntry> entries = jar.entries();
				Set<String> set = new TreeSet<String>();
				while (entries.hasMoreElements()) {
					String entry = entries.nextElement().toString();

					if (entry.contains(path) && entry.contains("Comando") &&
							!entry.contains("Test") && !entry.contains("NonValido") && !entry.contains("Abstract")) {

						entry = entry
								.replace(path + "/", "")
								.replace("Comando", "")
								.replace(".class", "");
						
						if(!entry.equals("")) {
							set.add(entry);
						}
					}
				}for (String comando : set) {
				    io.mostraMessaggio(comando);
				}
				jar.close();
			} catch (IOException e)  {
				io.mostraMessaggio("Errore durante la lettura del JAR");
				e.printStackTrace();
			};
		} 
		else {

			// Gestione da filesystem
			File directory = new File(resource.getFile());
			if (directory.exists()) {
				for (String file : directory.list()) {
					if (file.startsWith("Comando") && 
							!file.contains("Test") && !file.contains("NonValido")) {

						file = file
								.replace("Comando", "")
								.replace(".class", "");
						
						if(!file.equals("")) {
							io.mostraMessaggio(file);
						}
					}
				}
			}
		}
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
}
