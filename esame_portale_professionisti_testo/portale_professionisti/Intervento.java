package portale_professionisti;

public class Intervento {
	private Utente u;
	private String tipologia ;
	private String descrizione;
	private int codiceIntervento;
	public Intervento(int codiceIntervento, Utente u, String tipologia, String descrizione) {
		super();
		this.u = u;
		this.tipologia = tipologia;
		this.descrizione = descrizione;
		this.codiceIntervento = codiceIntervento;
	}
	
	
	public int getCodiceIntervento() {
		return codiceIntervento;
	}


	public void setCodiceIntervento(int codiceIntervento) {
		this.codiceIntervento = codiceIntervento;
	}


	public Utente getU() {
		return u;
	}
	public void setU(Utente u) {
		this.u = u;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
