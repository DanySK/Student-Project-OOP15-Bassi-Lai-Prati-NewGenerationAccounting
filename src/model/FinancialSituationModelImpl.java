package model;

import java.util.ArrayList;
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
	private LinkedList<Account> SP;
	private LinkedList<Account> CE;
	private DBDataModel db;
	private LinkedList<Account> contiRegistrati;
	private Map<String, Float> indiciMargini = new HashMap<>();
	private float ricVend = 0;
	private float capSociale = 0;
	private float ammortamenti = 0;

	public FinancialSituationModelImpl(DBDataModel db) {
		this.db = db;
		SP = new LinkedList<Account>();
		CE = new LinkedList<Account>();
		contiRegistrati = db.getAccounts();
	}

	private LinkedList<Account> calcolaCE() {

		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.COSTO) {
				for (Sections s : Sections.getCosti()) {
					if (a.getSezione() == s) {
						SP.add(a);
					}
				}
			}
		}
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.RICAVO) {
				for (Sections s : Sections.getRicavi()) {
					if (a.getSezione() == s) {
						CE.add(a);
					}
				}
			}
		}
		return CE;
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

	private LinkedList<Account> calcolaSP() {
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.ATTIVITA) {
				for (Sections s : Sections.getAttivita()) {
					if (a.getSezione() == s) {
						SP.add(a);
					}
				}
			}
		}
		for (Account a : contiRegistrati) {
			if (a.getNatura() == Natures.PASSIVITA) {
				for (Sections s : Sections.getPassivita()) {
					if (a.getSezione() == s) {
						SP.add(a);
					}
				}
			}
		}
		return SP;
	}

	@Override
	public LinkedList<Account> getCE() {
		return calcolaCE();
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
	
    @Override
    public String AnalisiFinanziaria() {
        calcolaIndici_Margini();
        ArrayList<String> commento = new ArrayList<>();
        // FINIRE
        return null;
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
	public LinkedList<Account> getSP() {
		return calcolaSP();
	}

	public DBDataModel saveDBAndClose() {
		return db;
	}

}
