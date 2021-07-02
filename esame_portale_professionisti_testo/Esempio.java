import java.util.*;

import portale_professionisti.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneNessunaOfferta {

		Portale po = new Portale();
		
		System.out.println("/************************************/");
		System.out.println("/**            R1. Utenti          **/");
		System.out.println("/************************************/");

		System.out.println("\n* Registrazione utente");
		
		Utente u1 = po.registraUtente("Verdi", "Luigi", "19750523", "Roma");
		
		System.out.println("\n* Informazioni utente\n");
		System.out.println("Identificativo: "+u1.getIdUtente());
		System.out.println("Cognome: "+u1.getCognome());
		System.out.println("Nome: "+u1.getNome());
		System.out.println("Data di nascita: "+u1.getDataNascita());
		System.out.println("Citta': "+u1.getCitta());

		System.out.println("\n* Registrazione altri utenti");
		po.registraUtente("Rossi", "Mario", "20021106", "Napoli");
		po.registraUtente("Verdi", "Anna", "19830205", "Catania");
		po.registraUtente("Rossi", "Mario", "19420101", "Bari");
		po.registraUtente("Bianchi", "Alberto", "19630911", "Napoli");

		
		System.out.println("\n* Ricerca utente con identificativo ROSMAR2");
		Utente uTrovato = po.cercaUtente("ROSMAR2");
		
		System.out.println("\n* Informazioni utente trovato\n");
		System.out.println("Identificativo: "+uTrovato.getIdUtente());
		System.out.println("Cognome: "+uTrovato.getCognome());
		System.out.println("Nome: "+uTrovato.getNome());
		System.out.println("Data di nascita: "+uTrovato.getDataNascita());
		System.out.println("Citta': "+uTrovato.getCitta());

		System.out.println("\n* Cerca utenti di Napoli (ordine di inserimento)");
		LinkedList<Utente> listaUtenti = new LinkedList<Utente>(po.cercaUtenti("Napoli"));
		for(Utente u : listaUtenti) {
			System.out.println(""+u.getCognome()+" "+u.getNome()+" "+u.getDataNascita()+" "+u.getCitta()+" "+u.getIdUtente()+"");
		}
			
		System.out.println("\n* Elenco utenti (ordine di cognome, nome, ordine inserimento se omonimi)");
						   listaUtenti = new LinkedList<Utente>(po.elencoUtenti());
		for(Utente u : listaUtenti) {
			System.out.println(""+u.getCognome()+" "+u.getNome()+" "+u.getDataNascita()+" "+u.getCitta()+" "+u.getIdUtente()+"");
		}
			
		
		System.out.println("\n\n/************************************/");
		System.out.println("/**        R2. Professionisti      **/");
		System.out.println("/************************************/");

		System.out.println("\n* Registrazione professionista");

		Professionista p1 = po.registraProfessionista("Illuminato", "Matteo", "ELE", "Roma");
		System.out.println("\n* Informazioni professionista\n");
		System.out.println("Identificativo: "+p1.getIdProfessionista());
		System.out.println("Cognome: "+p1.getCognome());
		System.out.println("Nome: "+u1.getNome());
		if(p1 instanceof Elettricista)
			System.out.println("Categoria: Elettricista");
		else if(p1 instanceof Idraulico)
			System.out.println("Categoria: Idraulico");
		System.out.println("Citta: "+u1.getCitta());

		System.out.println("\n* Registrazione altri professionisti");

		po.registraProfessionista("Sottacqua", "Luigi", "IDR", "Napoli");
		po.registraProfessionista("Fulminata", "Anna", "ELE", "Roma");
		po.registraProfessionista("Generica", "Michela", "", "Torino");
		po.registraProfessionista("Asciutto", "Marco", "IDR", "Napoli");
		po.registraProfessionista("Tuttofare", "Angelo", "", "Bari");
		
		System.out.println("\n* Elenco professionisti (elettricisti, idraulici e poi generici, in ordine di inserimento)");
		LinkedList<Professionista> listaProfessionisti = new LinkedList<Professionista>(po.elencoProfessionistiPerTipologiaOrdineInserimento());
		for(Professionista p : listaProfessionisti) {
			if(p instanceof Elettricista)
				System.out.println(""+p.getCognome()+" "+p.getNome()+" "+p.getCitta()+" Elettricista");
			else if(p instanceof Idraulico)
				System.out.println(""+p.getCognome()+" "+p.getNome()+" "+p.getCitta()+" Idraulico");
			else
				System.out.println(""+p.getCognome()+" "+p.getNome()+" "+p.getCitta()+" Generico");
		}
		
		System.out.println("\n* Elenco professionisti (elettricisti, idraulici e poi generici, in ordine di ctta' e di inserimento)");
							       listaProfessionisti = new LinkedList<Professionista>(po.elencoProfessionistiPerTipologiaCittaOrdineInserimento());

		for(Professionista p : listaProfessionisti) {
			if(p instanceof Elettricista)
				System.out.println(""+p.getIdProfessionista()+" "+p.getCognome()+" "+p.getNome()+" "+p.getCitta()+", Elettricista");
			else if(p instanceof Idraulico)
				System.out.println(""+p.getIdProfessionista()+" "+p.getCognome()+" "+p.getNome()+" "+p.getCitta()+", Idraulico");
			else
				System.out.println(""+p.getIdProfessionista()+" "+p.getCognome()+" "+p.getNome()+" "+p.getCitta()+" Generico");
		}

		              
		System.out.println("\n\n/**********************************************/");
		System.out.println("/**  R3. Richieste ed offerte di intervento  **/");
		System.out.println("/**********************************************/");

		System.out.println("\n* Pubblicazione richiesta intervento");
		
		int idRichiestaIntervento = po.pubblicaRichiestaIntervento("VERLUI1", "ELE", "Sostituzione lampadina scale");

		System.out.println("\n* Codice assegnato alla richiesta:\n");
		
		System.out.println(idRichiestaIntervento);
		
		System.out.println("\n* Dettagli richiesta intervento 1000\n");
		
		String stringaDettagliRichiestaIntervento = po.stampaDettagliRichiestaIntervento(1000);

		System.out.println(stringaDettagliRichiestaIntervento);
		
		System.out.println("\n* Pubblicazione altre richieste intervento");

		po.pubblicaRichiestaIntervento("BIAALB1", "ELE", "Installazione LED sotto-pensile");
		po.pubblicaRichiestaIntervento("ROSMAR1", "IDR", "Riparazione tubazioni");
		po.pubblicaRichiestaIntervento("BIAALB1", "", "Tinteggiatura ringhiera");

		System.out.println("\n* Dettagli richieste intervento (ordine di inserimento)\n");

		String stringaDettagliRichiesteIntervento = po.stampaDettagliRichiesteIntervento();

		System.out.println(stringaDettagliRichiesteIntervento);
		
		System.out.println("\n* Risposta a/Offerta per richiesta intervento 1000 da ELE1 costo 130.0");
		
		boolean inserimentoRisposta = po.rispondiConOffertaPerIntervento(1000, "ELE1", 130.00);
		
		System.out.println("\n* Esito inserimento risposta a/offerta:");
		
		if(inserimentoRisposta)
			System.out.println("\nInserita");
		else
			System.out.println("\nNon inserita");

		System.out.println("\n* Altre risposte a/Offerte per richiesta intervento 1000");

		po.rispondiConOffertaPerIntervento(1000, "ELE2", 110.00);
		po.rispondiConOffertaPerIntervento(1000, "IDR1", 50.00);
		po.rispondiConOffertaPerIntervento(1000, "PRO2", 20.00);

		System.out.println("\n* Risposte a/Offerte per intervento 1000 (costo crescente)\n");
		
		String stringaRisposteOffertePerIntervento = po.risposteOffertePerIntervento(1000);
		
		System.out.println(stringaRisposteOffertePerIntervento);

		
		System.out.println("\n\n/******************************************************/");
		System.out.println("/**  R4. Selezione offerte ed esecuzione intervento  **/");
		System.out.println("/******************************************************/");
		
		System.out.println("\n* Costo migliore offerta per intervento 1000\n");
		
		double costoMiglioreOffertaPerIntervento = po.costoMiglioreOffertaPerIntervento(1000);
		
		System.out.println(costoMiglioreOffertaPerIntervento);
		
		System.out.println("\n* Identificativo professionista migliore offerta per intervento 1000\n");
		
		String idProfessionistaMiglioreOffertaPerIntervento = po.idProfessionistaMiglioreOffertaPerIntervento(1000);
		
		System.out.println(idProfessionistaMiglioreOffertaPerIntervento);
		
		System.out.println("\n* Esecuzione intervento 1000 con offerta migliore in data 20210628\n");
		po.eseguiIntervento(1000, "20210628");
		
		System.out.println("* Interventi per identificativo ELE2 (ordine di codice richiesta intervento)\n");
		
		String stringaInterventi = po.stampaInterventi("ELE2");
		
		System.out.println(stringaInterventi);


	}

}
