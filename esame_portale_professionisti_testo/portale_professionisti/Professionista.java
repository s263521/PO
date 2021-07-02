package portale_professionisti;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Professionista {

	private String idProfessionista;
	private String cognome;
	private String nome;
	private String citta;
	

	
	public Professionista(String idProfessionista, String cognome, String nome,
			String citta) {
		super();
		this.idProfessionista = idProfessionista;
		this.cognome = cognome;
		this.nome = nome;
		this.citta = citta;
		
		
	}

	

	public String getIdProfessionista() {
		return idProfessionista;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getCitta() {
		return citta;
	}

}
