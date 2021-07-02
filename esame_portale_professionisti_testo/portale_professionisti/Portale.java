package portale_professionisti;

import java.util.*;

public class Portale {

	Map<String, Utente> mappaUtenti;
	Map<String, Professionista> mappaProfessionisti;
	Map<Integer , Intervento> mappaInterventi;
	List<Offerta> listaOffertePerProfessionista;
	Map<Intervento, Professionista > mappaEseguiIntervento = new LinkedHashMap<>();
	
	
	public Portale() {
		mappaUtenti = new LinkedHashMap<String, Utente>();
		mappaProfessionisti = new LinkedHashMap<String, Professionista>();
		mappaInterventi = new LinkedHashMap<Integer, Intervento>();
		listaOffertePerProfessionista = new LinkedList<Offerta>();
	}
	public String assegnaCodiceUtente(String cognome, String nome) {
		int count=1;
		for(Utente u : mappaUtenti.values()) {
			if(u.getCognome().equals(cognome) && u.getNome().equals(nome)) {
				count++;
			}
		}
		if(count>0) {
			return cognome.substring(0,3).toUpperCase() + nome.substring(0, 3).toUpperCase() + count;
		} 
		
		return cognome.substring(0,3).toUpperCase() + nome.substring(0, 3).toUpperCase() ;
	}
	public Utente registraUtente(String cognome, String nome, String dataNascita, String citta) {
		String codiceUtente = assegnaCodiceUtente(cognome, nome);
		Utente u = new Utente(codiceUtente, cognome, nome, dataNascita, citta);
		mappaUtenti.put(codiceUtente, u);
		return u;
	}
	
	public void assegnaOfferta(Offerta o) {
		listaOffertePerProfessionista.add(o);
	}
	
	public Utente cercaUtente(String idUtente) {
		return mappaUtenti.get(idUtente);
	}

	public Collection<Utente> cercaUtenti(String citta){
		List<Utente> result = new LinkedList<Utente>();
		for(Utente u : mappaUtenti.values()) {
			if(u.getCitta().equals(citta)) {
				result.add(u);
			}
		}
		return result;
	}
	
	class ordinaPerCognomeNome implements Comparator<Utente> {

		@Override
		public int compare(Utente arg0, Utente arg1) {
			String u1 = arg0.getCognome() + arg0.getNome();
			String u2 = arg1.getCognome() + arg1.getNome();	
			return u1.compareTo(u2);
		}
		
	}
	public Collection<Utente> elencoUtenti(){
		List<Utente> result = new LinkedList<Utente>(mappaUtenti.values());
		Collections.sort(result, new ordinaPerCognomeNome());
		return result;
	}
	
	public String assegnaCodiceProfessionista(String tipologia) {
		if(tipologia.equals("ELE")) {
			int count = 1;
			for(Professionista p : mappaProfessionisti.values()) {
				if(p.getIdProfessionista().substring(0, 3).equals("ELE")) {
					count++;
				}
			}
			return "ELE" + count;
		}
		
		if(tipologia.equals("IDR")) {
			int count = 1;
			for(Professionista p : mappaProfessionisti.values()) {
				if(p.getIdProfessionista().substring(0, 3).equals("IDR")) {
					count++;
				}
			}
			return "IDR" + count;
		}
		
		if(tipologia.equals("")) {
			int count = 1;
			for(Professionista p : mappaProfessionisti.values()) {
				if(p.getIdProfessionista().substring(0, 3).equals("PRO")) {
					count++;
				}
			}
			return "PRO" + count;
		}
		return null;
	}
	public Professionista registraProfessionista(String cognome, String nome, String tipologia, String citta) {
		if(tipologia.equals("ELE")) {
			String idEle = assegnaCodiceProfessionista(tipologia);
			Professionista e = new Elettricista(idEle, cognome, nome, citta);
			mappaProfessionisti.put(idEle, e);
			return e;
		}
		
		if(tipologia.equals("IDR")) {
			String ididr = assegnaCodiceProfessionista(tipologia);
			Professionista i = new Idraulico(ididr, cognome, nome, citta);
			mappaProfessionisti.put(ididr, i);
			return i;
		}
		
		if(tipologia.equals("")) {
			String idPro = assegnaCodiceProfessionista(tipologia);
			Professionista p = new Professionista(idPro, cognome, nome, citta);
			mappaProfessionisti.put(idPro, p);
			return p;
		}
		
		return null;
	}
	class ordinaPerTipologia implements Comparator<Professionista> {

		@Override
		public int compare(Professionista arg0, Professionista arg1) {
			String p1 = arg0.getIdProfessionista().substring(0, 3);
			String p2 = arg1.getIdProfessionista().substring(0, 3);
			return p1.compareTo(p2);
		}
		
	}
	public Collection<Professionista> elencoProfessionistiPerTipologiaOrdineInserimento() {
		List<Professionista> result = new LinkedList<Professionista>(mappaProfessionisti.values());
		Collections.sort(result, new ordinaPerTipologia());
		return result;
	}

	class ordinaPerTipologiaCitta implements Comparator<Professionista> {

		@Override
		public int compare(Professionista arg0, Professionista arg1) {
			String p1 = arg0.getIdProfessionista().substring(0, 3);
			String p2 = arg1.getIdProfessionista().substring(0, 3);
			
			if(p1.equals(p2)) {
				p1 = arg0.getCitta();
				p2 = arg1.getCitta();
			}
			return p1.compareTo(p2);
		}
		
	}
	public Collection<Professionista> elencoProfessionistiPerTipologiaCittaOrdineInserimento() {
		List<Professionista> result = new LinkedList<Professionista>(mappaProfessionisti.values());
		Collections.sort(result, new ordinaPerTipologiaCitta());
		return result;
	}

	public int pubblicaRichiestaIntervento(String idUtente, String tipologia, String descrizione) {
		Utente u = mappaUtenti.get(idUtente);
		int codiceIntervento = 1000 + mappaInterventi.size();
		Intervento i = new Intervento(codiceIntervento, u, tipologia, descrizione);
		mappaInterventi.put(codiceIntervento, i);
		return codiceIntervento;
	}
	
	public String stampaDettagliRichiestaIntervento(int codiceRichiestaIntervento) {
		
		String s = "";
		for(Intervento i : mappaInterventi.values()) {
			if(i.getCodiceIntervento() == codiceRichiestaIntervento) {
				if(i.getTipologia().equals("ELE")) {
					s += i.getCodiceIntervento() + ", " + i.getU().getCognome() + ", " + i.getU().getNome() + ", " + i.getU().getCitta() + ", " + i.getDescrizione() + ", Elettricista";
				}
				if(i.getTipologia().equals("IDR")) {
					s += i.getCodiceIntervento() + ", " + i.getU().getCognome() + ", " + i.getU().getNome() + ", " + i.getU().getCitta() + ", " + i.getDescrizione() + ", Idraulico";
				}
				if(i.getTipologia().equals("")) {
					s += i.getCodiceIntervento() + ", " + i.getU().getCognome() + ", " + i.getU().getNome() + ", " + i.getU().getCitta() + ", " + i.getDescrizione() + ", Generico";
				}
			}
		}
		return s;
	}
	
	public String stampaDettagliRichiesteIntervento() {
		String s = "";
		for(Intervento i : mappaInterventi.values()) {
				if(i.getTipologia().equals("ELE")) {
					s += i.getCodiceIntervento() + ", " + i.getU().getCognome() + ", " + i.getU().getNome() + ", " + i.getU().getCitta() + ", " + i.getDescrizione() + ", Elettricista\n";
				}
				if(i.getTipologia().equals("IDR")) {
					s += i.getCodiceIntervento() + ", " + i.getU().getCognome() + ", " + i.getU().getNome() + ", " + i.getU().getCitta() + ", " + i.getDescrizione() + ", Idraulico\n";
				}
				if(i.getTipologia().equals("")) {
					s += i.getCodiceIntervento() + ", " + i.getU().getCognome() + ", " + i.getU().getNome() + ", " + i.getU().getCitta() + ", " + i.getDescrizione() + ", Generico\n";
				}
		}
		return s.substring(0, s.length()-1);
	}

	public boolean rispondiConOffertaPerIntervento(int codiceRichiestaIntervento, String idProfessionista, double costo) {
		Intervento i = mappaInterventi.get(codiceRichiestaIntervento);
		Professionista p = mappaProfessionisti.get(idProfessionista);
		
		if(i.getU().getCitta().equals(p.getCitta()) && (p.getIdProfessionista().contains(i.getTipologia()) || p.getIdProfessionista().contains("PRO"))) {
			Offerta o = new Offerta(p, i, i.getU(), costo);
			assegnaOfferta(o);
			return true;
		}
		return false;
	}
	
	class ordinaPerCosto implements Comparator<Offerta> {

		@Override
		public int compare(Offerta arg0, Offerta arg1) {
			double o1 = arg0.getCosto();
			double o2 = arg1.getCosto();
			return (int) (o1-o2);
		}
		
	}
	public String risposteOffertePerIntervento(int codiceRichiestaIntervento) {
		Intervento i = mappaInterventi.get(codiceRichiestaIntervento);
		String s = "";
		List<Offerta> result = new LinkedList<Offerta>(listaOffertePerProfessionista);
		Collections.sort(result, new ordinaPerCosto() );
		for(Offerta o : result) {
			if(o.getI().getCodiceIntervento()==codiceRichiestaIntervento) {
				s += o.getP().getIdProfessionista() + ", " + o.getP().getCognome() + ", " + o.getP().getNome() + ", " + o.getP().getCitta() + ", " + o.getCosto() + "\n";
			}
		}
		return s;
	}
	
	public double costoMiglioreOffertaPerIntervento(int codiceRichiestaIntervento) throws EccezioneNessunaOfferta {
		Intervento i = mappaInterventi.get(codiceRichiestaIntervento);
		double max = 10000000;
		for(Offerta o : listaOffertePerProfessionista) {
			if(o.getI().getCodiceIntervento()==codiceRichiestaIntervento) {
				if(o.getCosto()<max) {
					max = o.getCosto();
				}
			} 
		}
		if(max==10000000) {
			throw new EccezioneNessunaOfferta();
		}
		return max;
	}
	
	public String idProfessionistaMiglioreOffertaPerIntervento(int codiceRichiestaIntervento) throws EccezioneNessunaOfferta {
		Intervento i = mappaInterventi.get(codiceRichiestaIntervento);
		String s = "";
		for(Offerta o : listaOffertePerProfessionista) {
			if(o.getI().getCodiceIntervento()==codiceRichiestaIntervento) {
				 s = o.getP().getIdProfessionista();
			}
		}
		if(s=="") {
			throw new EccezioneNessunaOfferta();
		}
		return s;
	}
	
	
	public void eseguiIntervento(int codiceRichiestaIntervento, String dataEsecuzione) throws EccezioneNessunaOfferta {
		Intervento i = mappaInterventi.get(codiceRichiestaIntervento);
		Professionista p = mappaProfessionisti.get(idProfessionistaMiglioreOffertaPerIntervento(codiceRichiestaIntervento));
		for(Offerta o : listaOffertePerProfessionista) {
			if(o.getP() == p && o.getI() == i) {
				o.setData(dataEsecuzione);
			}
		}
	}
	
	public String stampaInterventi(String idProfessionistaOppureUtente) throws EccezioneNessunaOfferta{
		Professionista p = mappaProfessionisti.get(idProfessionistaOppureUtente);
		String s = "";
		if(p!=null) {
			for(Offerta o : listaOffertePerProfessionista) {
				if(o.getP()==p && o.getData()!=null) 
					s += o.getI().getCodiceIntervento() + ", " + o.getData() + ", " + o.getP().getIdProfessionista() + ", " + o.getU().getIdUtente() + ", " + o.getCosto() + ";";
			}
		} else {
			for(Offerta o : listaOffertePerProfessionista) {
				if(o.getU().getIdUtente().equals(idProfessionistaOppureUtente) && o.getData()!=null) 
					s += o.getI().getCodiceIntervento() + ", " + o.getData() + ", " + o.getP().getIdProfessionista() + ", " + o.getU().getIdUtente() + ", " + o.getCosto() + ";";
			}
		}
		return s;
	}
}




