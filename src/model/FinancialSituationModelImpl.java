package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import dataEnum.Natures;
import dataEnum.Sections;
import dataModel.Account;
import dataModel.DBDataModel;

/**
 * classe implementativa per la gestione di stato patrimoniale conto economico e
 * analisi per margini e indici
 * 
 * @author niky
 *
 */
public class FinancialSituationModelImpl implements IFinancialSituationModel {

	private static String ROI;
	private static String ROS;
	private static String leverage;
	private static String ROT;
	private static String ROE;
	private static String margineStrutturaPrimario;
	private static String margineStrutturaSecondario;
	private static String margineTesoreria;
	private DBDataModel db;
	private LinkedList<Account> contiRegistrati;
	private Map<String, Float> indiciMargini = new HashMap<>();
	private float ricVend = 0;
	private float capSociale = 0;
	private float ammortamenti = 0;

	public FinancialSituationModelImpl(DBDataModel db) {
		this.db = db;
		contiRegistrati = db.getAccounts();
	}

	@Override
	public String AnalisiFinanziaria() {
		calcolaIndici_Margini();
		String commento = "";
		// controllo ROI
		if (indiciMargini.get(ROI) >= 4) {
			commento += "<b>Tasso di redditività del capitale investito</B> = " + indiciMargini.get(ROI)+ "%."
			                +" Indica buona redditività del capitale invesitito <br/>";
		} else {
			commento += "<b>Tasso di redditività del capitale investito</B> = " + indiciMargini.get(ROI)+ "%."
			                +" Indica pessima redditività del capitale investito <br/>";
		}
		// controllo ROS
		if (indiciMargini.get(ROS) > 0) {
			commento += "<b>Tasso di redditività delle vendite</B> = " + indiciMargini.get(ROS)+ "%."
                                        +" Indica che i ricavi sono in grado di coprire tutte le spese ordinarie e anche quelle extra<br/>";
		} else if (indiciMargini.get(ROS) == 0) {
			commento += "<b>Tasso di redditività delle vendite</B> = " + indiciMargini.get(ROS)+ "%."+" Indica che i ricavi coprono solo le spese ordinarie<br/>";
		} else {
			commento += "<b>Tasso di redditività delle vendite</B> = " + indiciMargini.get(ROS)+ "%."+" Indica che i ricavi non riescono a coprire nemmeno le spese ordinarie<br/>";
		}
		// controllo ROT
		if (indiciMargini.get(ROT) > 12) {
			commento += "<b>Indice di rotazione degli impieghi</B> = "+indiciMargini.get(ROT)+ "%."+" Indica alta capacità di ritorno del capitale investito tramite le vendite<br/>";
		} else {
			commento += "<b>Indice di rotazione degli impieghi</B> = "+indiciMargini.get(ROT)+ "%."+" Indica bassa capacità di ritorno del capitale investito tramite le vendite<br/>";
		}
		// controllo ROE
		if (indiciMargini.get(ROE) > 6) {
			commento += "<b>Tasso di redditività del capitale proprio</B> = "+indiciMargini.get(ROE)+ "%."+" Indica buona redditività del capitale proprio<br/>";
		} else {
			commento += "<b>Tasso di redditività del capitale proprio</B> = "+indiciMargini.get(ROE)+ "%."+" Indica cattiva redditività del capitale proprio<br/>";
		}
		// controllo Leverage
		if (indiciMargini.get(leverage) == 1) {
			commento += "<b>Indice di indebitamento </B> = "+indiciMargini.get(leverage)+"%"+" Indica che gli investimenti sono finanziati dal Capitale Proprio<br/>";
		} else if (indiciMargini.get(leverage) > 1 && indiciMargini.get(leverage) < 2) {
			commento += "<b>Indice di indebitamento </B> = "+indiciMargini.get(leverage)+"%"+" Indica che esiste un buon rapporto tra capitale Proprio e capitale di terzi <br/>";
		} else if (indiciMargini.get(leverage) > 2) {
			commento += "<b>Indice di indebitamento </B> = "+indiciMargini.get(leverage)+"%"+" Indica un indebitamento <br/>";
		}
		// controllo margine di struttura primario
		if (indiciMargini.get(margineStrutturaPrimario) > 0) {
			commento += "<b>Margine di struttura primario </B> = "+indiciMargini.get(margineStrutturaPrimario)+"%"+" Indica che il capitale permanente finanzia anche una parte delle attività circolanti. <br/>";
		} else if (indiciMargini.get(margineStrutturaPrimario) == 0) {
			commento += "<b>Margine di struttura primario </B> = "+indiciMargini.get(margineStrutturaPrimario)+"%"+" Indica che le attività immobilizzate sono coperte dal capitale proprio, ma senza extra. <br/>";
		} else {
			commento += "<b>Margine di struttura primario </B> = "+indiciMargini.get(margineStrutturaPrimario)+"%"+" Indica che l'impresa si trova in una situazione di dipendenza finanziaria,"
					+ "la parte mancante dovrà essere coperta anche con risorse di terzi<br/>";
		}
		// controllo margine di struttura secondario
		if (indiciMargini.get(margineStrutturaSecondario) > 0) {
			commento += "<b>Margine di struttura secondario </B> = "+indiciMargini.get(margineStrutturaSecondario)+"%"+" Indica che la struttura fonti-impieghi risulta equilibrata,"
					+ "i capitali permanenti risultano superiori alle attività fisse <br/>";
		} else {
			commento += "<b>Margine di struttura secondario </B> = "+indiciMargini.get(margineStrutturaSecondario)+"%"+ " Indica che il Capitale permanente non finanzia interamente le attività immobilizzate <br/>";
		}
		// controllo margine di tesoreria
		if (indiciMargini.get(margineTesoreria) > 0) {
			commento += "<b>Margine di tesoreria </B> = "+indiciMargini.get(margineTesoreria)+"%"+" Indica equilibrio finanziario per quello che riguarda le liquidità a breve termine <br/>";
		} else {
			commento += "<b>Margine di tesoreria </B> = "+indiciMargini.get(margineTesoreria)+"%"+" Indica squilibrio finanziario per quello che riguarda le liquidità a breve termine <br/>";
		}
		return commento;
	}

	@Override
	public String Attivita() {
		String Attivita = "";
		for (Sections s : Sections.getAttivita()) {
		    Attivita += "</br><b>" + s + "</B><br/>"; 
		    for (int j = 0; j < contiRegistrati.size(); j++) {
			// scorro gli elementi della lista originale
			if (contiRegistrati.get(j).getNatura() == Natures.ATTIVITA
					&& contiRegistrati.get(j).getSezione() == s) {
				Attivita += contiRegistrati.get(j).getName()+"<br/>";
			}
		}
		    Attivita += "<b>totale</B><br/>";
	}
		return Attivita;
}

	private Map<String, Float> calcolaIndici_Margini() {
		indiciMargini.put(ROS, getROS());
		indiciMargini.put(ROT, getROT());
		indiciMargini.put(ROI, getROI());
		indiciMargini.put(ROE, getROE());
		indiciMargini.put(leverage, getLeverage());
		indiciMargini.put(margineStrutturaPrimario, getMargineStrutturaPrimario());
		indiciMargini.put(margineStrutturaSecondario, getMargineStrutturaSecondario());
		indiciMargini.put(margineTesoreria, getMargineTesoreria());
		return indiciMargini;

	}

	@Override
	public String Costi() {
		String Costi = "";
		for (Sections s : Sections.getCosti()) {
			Costi += "<b>" + s + "</B>\n";
			for (int j = 0; j < contiRegistrati.size(); j++) {
				if (contiRegistrati.get(j).getNatura() == Natures.COSTO
					&& contiRegistrati.get(j).getSezione() == s) {
				    Costi += contiRegistrati.get(j).getName() + "<br/>";
				}
			}
		}
		return Costi;
	}

	private Float getLeverage() {
		float totAttivo = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA) {
				totAttivo += a.getSaldo();
			}
		}
		return totAttivo / capSociale;
	}

	private Float getMargineStrutturaPrimario() {
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.COSTO && a.getSezione() == Sections.COSTI_DELLA_PRODUZIONE
					&& a.getName().contains("ammortamento")) {
				ammortamenti += a.getSaldo();
			}
		}
		return capSociale - ammortamenti;
	}

	private Float getMargineStrutturaSecondario() {
		float debM_Lterm = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.PASSIVITA && a.getSezione() == Sections.FONDI_RISCHI_E_ONERI
					&& a.getName().contains("fondo")) {
				debM_Lterm += a.getSaldo();
			}
		}
		return (capSociale + debM_Lterm) - ammortamenti;
	}

	private Float getMargineTesoreria() {
		float dispLiq = 0;
		float debBterm = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA && a.getSezione() == Sections.DISPONIBILITA_LIQUIDE)
				dispLiq += a.getSaldo();
			else if (a.getNatura() == Natures.PASSIVITA && a.getSezione() == Sections.DEBITI) {
				if (a.getName().contains("vs fornitori") || a.getName().contains("vs banche")
						|| a.getName().contains("vs controllanti")) {
					debBterm += a.getSaldo();
				} else if (a.getSezione() == Sections.RATEI_E_RISCONTI_PASSIVI)
					debBterm += a.getSaldo();
				else if (a.getSezione() == Sections.TFR)
					debBterm += a.getSaldo();
			}
		}
		return dispLiq - debBterm;
	}

	private Float getROE() {
		float valDellaProd = 0;
		float costDellaProd = 0;
		float provFin = 0;
		float oneriFin = 0;
		float rival = 0;
		float sval = 0;
		float provStr = 0;
		float oneriStr = 0;

		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.RICAVO) {
				if (a.getSezione() == Sections.VALORE_DELLA_PRODUZIONE)
					valDellaProd += a.getSaldo();
				else if (a.getSezione() == Sections.PROVENTI_FINANZIARI)
					provFin += a.getSaldo();
				else if (a.getSezione() == Sections.RIVALUTAZIONI)
					rival += a.getSaldo();
				else if (a.getSezione() == Sections.PROVENTI_STRAORDINARI)
					provStr += a.getSaldo();
			} else if (a.getNatura() == Natures.COSTO) {
				if (a.getSezione() == Sections.COSTI_DELLA_PRODUZIONE)
					costDellaProd += a.getSaldo();
				else if (a.getSezione() == Sections.ONERI_FINANZIARI)
					oneriFin += a.getSaldo();
				else if (a.getSezione() == Sections.SVALUTAZIONI)
					sval += a.getSaldo();
				else if (a.getSezione() == Sections.ONERI_STRAORDINARI)
					oneriStr += a.getSaldo();
			}
		}
		return (valDellaProd - costDellaProd + provFin - oneriFin + rival - sval + provStr - oneriStr) / capSociale;
	}

	private Float getROI() {
		float roi = 0;
		roi = (getROS() * getROT());
		return roi;
	}

	private Float getROS() {
		float costiAcq = 0;
		for (Account a : contiRegistrati) {
			if (a.getName() == "ricavi delle vendite" && a.getNatura() == Natures.RICAVO
					&& a.getSezione() == Sections.VALORE_DELLA_PRODUZIONE) {
				ricVend += a.getSaldo();
			} else if (a.getNatura() == Natures.COSTO) {
				costiAcq += a.getSaldo();
			}
		}
		return (ricVend - costiAcq) / ricVend;
	}

	private Float getROT() {
		float scorte = 0;
		float cassa = 0;
		float crediti = 0;

		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA && a.getSezione() == Sections.RIMANENZE) {
				scorte += a.getSaldo();
			} else if (a.getName().contains("cassa") && a.getNatura() == Natures.ATTIVITA
					&& a.getSezione() == Sections.DISPONIBILITA_LIQUIDE) {
				cassa += a.getSaldo();
			} else if (a.getSezione() == Sections.CREDITI) {
				crediti += a.getSaldo();
			} else if (a.getSezione() == Sections.PATRIMONIO_NETTO && a.getName().contains("capitale")) {
				capSociale += a.getSaldo();
			}
		}

		return ricVend / (scorte + cassa + crediti + capSociale);
	}

	@Override
	public Float getSaldo_Conto_Ec() {
		float totCosti = 0;
		float totRicavi = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.COSTO && Sections.getCosti().contains(a.getSezione())) {
				totCosti += a.getSaldo();
			} else if (a.getNatura() == Natures.PASSIVITA && Sections.getRicavi().contains(a.getSezione())) {
				totRicavi += a.getSaldo();
			}
		}
		return totRicavi - totCosti;
	}

	@Override
	public Float getSaldo_Stato_Patr() {
		float totAtt = 0;
		float totPass = 0;
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA && Sections.getAttivita().contains(a.getSezione())) {
				totAtt += a.getSaldo();
			} else if (a.getNatura() == Natures.PASSIVITA && Sections.getPassivita().contains(a.getSezione())) {
				totPass += a.getSaldo();
			}
		}
		if (totAtt != totPass) {
			throw new IllegalArgumentException("saldo attività != saldo passività");
		} else {
			return totAtt;
		}

	}

	@Override
	public String Passivita() {
		String Passivita = "";
		for (Sections s : Sections.getPassivita()) {
		    Passivita += "<b>"+ s +"</B><br/>";
			for (int j = 0; j < contiRegistrati.size(); j++) { 
			 // scorro gli elementi della lista principale
                           if (contiRegistrati.get(j).getNatura() == Natures.PASSIVITA
					&& contiRegistrati.get(j).getSezione() == s) {
					Passivita += contiRegistrati.get(j).getName()+ "<br/>";
				}
			}
		}
		return Passivita;
	}

	@Override
	public String Ricavi() {
		String Ricavi = "";
		for (Sections s : Sections.getRicavi()) {
			Ricavi += "<b>" + s + "</B>\n";
			for (int j = 0; j < contiRegistrati.size(); j++) {
				if (contiRegistrati.get(j).getNatura() == Natures.RICAVO
						&& contiRegistrati.get(j).getSezione() == s) {
					Ricavi += contiRegistrati.get(j).getName()+ "<br/>";
				}
			}
		}
		return Ricavi;
	}

	@Override
	public String Saldi_Attivita() {
		float totSezione = 0;
		String saldi_attivita = "";
		for (Sections elem : Sections.getAttivita()) {
			saldi_attivita = "<br/>";
			System.out.println(totSezione);
			for (Account a : contiRegistrati) {
				if (a.getNatura() == Natures.ATTIVITA && a.getSezione() == elem) {
					totSezione += a.getSaldo();
					saldi_attivita += a.getSaldo()+ "<br/>";
				}
			}
			saldi_attivita += "<b>"+totSezione+"</B>";
			totSezione = 0;
		}
		return saldi_attivita;
	}

	@Override
	public String Saldi_Costi() {
		float totSezione = 0;
		String saldi_Costi = "";
		for (Sections elem : Sections.getCosti()) {
			saldi_Costi = "<b>" + Float.toString(totSezione) + "</B><br/>";
			for (Account a : contiRegistrati) {
				if (a.getNatura() == Natures.COSTO && a.getSezione() == elem) {
					totSezione += a.getSaldo();
					saldi_Costi += a.getSaldo() + "<br/>";
				}
			}
			totSezione = 0;
		}
		return saldi_Costi;
	}

	@Override
	public String Saldi_Passivita() {
		float totSezione = 0;
		String saldi_passivita = "";
		for (Sections elem : Sections.getPassivita()) {
			saldi_passivita = "<b>" + Float.toString(totSezione) + "</B><br/>";
			totSezione = 0;
			for (Account a : contiRegistrati) {
				if (a.getNatura() == Natures.PASSIVITA && a.getSezione() == elem) {
					totSezione += a.getSaldo();
					saldi_passivita += a.getSaldo() + "<br/>";
				}
			}
			
		}
		return saldi_passivita;
	}

	@Override
	public String Saldi_Ricavi() {
		float totSezione = 0;
		String saldi_Ricavi = "";
		for (Sections elem : Sections.getRicavi()) {
			saldi_Ricavi = "<b>" + Float.toString(totSezione) + "</B><br/>";
			for (Account a : contiRegistrati) {
				if (a.getNatura() == Natures.RICAVO && a.getSezione() == elem) {
					totSezione += a.getSaldo();
					saldi_Ricavi += a.getSaldo() + "<br/>";
				}
			}
			totSezione = 0;
		}
		return saldi_Ricavi;
	}

	public DBDataModel saveDBAndClose() {
		return db;
	}

}
