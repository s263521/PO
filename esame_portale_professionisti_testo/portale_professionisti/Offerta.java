package portale_professionisti;

public class Offerta {
	
	private Intervento i ;
	private Professionista p;
	private Utente u;
	private double costo;
	private String data;
	
	public Offerta(Professionista p ,Intervento i, Utente u, double costo) {
		super();
		this.u = u;
		this.costo = costo;
		this.i = i;
		this.p = p;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public Professionista getP() {
		return p;
	}



	public void setP(Professionista p) {
		this.p = p;
	}



	public Intervento getI() {
		return i;
	}


	public void setI(Intervento i) {
		this.i = i;
	}


	public Utente getU() {
		return u;
	}

	public void setU(Utente u) {
		this.u = u;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	
	
	

}
