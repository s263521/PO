package portale_professionisti;

public class Utente {
	
	private String idUtente;
	private String cognome;
	private String nome;
	private String dataNascita;
	private String citta;
	
	public Utente(String idUtente, String cognome, String nome,
			String dataNascita, String citta) {
		super();
		this.idUtente = idUtente;
		this.cognome = cognome;
		this.nome = nome;
		this.dataNascita = dataNascita;
		this.citta = citta;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getDataNascita() {
		return dataNascita;
	}
	
	public String getCitta() {
		return citta;
	}
	
	
	
}
