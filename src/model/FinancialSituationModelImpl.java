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

	@Override
	public String AnalisiFinanziaria() {
		calcolaIndici_Margini();
		String commento = new String();
		return null;
	}
	
        private String getCE() {
	    String Conto_Economico = new String();
	    LinkedList<Account> ce = new LinkedList<>();
	    ce = calcolaCE();
	    for(Account a: ce){
	        Conto_Economico.concat(a.toString() + "\n");
	    }
            return Conto_Economico;
        }
	
        private String getSP() {
	    String Stato_Patrimoniale = new String();
            LinkedList<Account> sp = new LinkedList<>();
            sp = calcolaSP();
            for(Account a: sp){
                Stato_Patrimoniale.concat(a.toString() + "\n");
            }
            return Stato_Patrimoniale;
        }

        public DBDataModel saveDBAndClose() {
                return db;
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
    public String Attivita() {
        String Attivita = "<b>CREDITI_VS_SOCI</B>\n";
        for(int i = 1; i<=9;i++){
            for(int j = 0; j<db.getAccounts().size(); j++){ // scorro gli elementi della lista originale
                if(db.getAccounts().get(j).getNatura() == Natures.ATTIVITA &&
                                db.getAccounts().get(j).getSezione()==Sections.valueOf(Integer.toString(i))){
                    Attivita += "\n" + db.getAccounts().get(j).getName();
                }
            }
            Attivita+="\n <b>"+ Sections.valueOf(Integer.toString(i+1))+ "</B>\n";
        }
        return Attivita;
    }

    @Override
    public String Saldi_Attivita() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String Passivita() {
        String Passivita = "<b>PATRIMONIO_NETTO</B>\n";
        for(int i = 10; i<=14;i++){
            for(int j = 0; j<db.getAccounts().size(); j++){ // scorro gli elementi della lista originale
                if(db.getAccounts().get(j).getNatura() == Natures.PASSIVITA &&
                                db.getAccounts().get(j).getSezione()==Sections.valueOf(Integer.toString(i))){
                    Passivita += "\n" + db.getAccounts().get(j).getName();
                }
            }
            Passivita+="\n <b>"+ Sections.valueOf(Integer.toString(i+1))+ "</B>\n";
        }
        return Passivita;
    }

    @Override
    public String Saldi_Passivita() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String Costi() {
        String Costi = "<b>COSTI_DELLA_PRODUZIONE</B>\n";
        for(int i = 15; i<=19;i++){
            for(int j = 0; j<db.getAccounts().size(); j++){ // scorro gli elementi della lista originale
                if(db.getAccounts().get(j).getNatura() == Natures.COSTO &&
                                db.getAccounts().get(j).getSezione()==Sections.valueOf(Integer.toString(i))){
                    Costi += "\n" + db.getAccounts().get(j).getName();
                }
            }
            Costi+="\n <b>"+ Sections.valueOf(Integer.toString(i+1))+ "</B>\n";
        }
        return Costi;
    }

    @Override
    public String Saldi_Costi() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String Ricavi() {
        String Ricavi= "<b>VALORE_DELLA_PRODUZIONE</B>\n";
        for(int i = 20; i<=23;i++){
            for(int j = 0; j<db.getAccounts().size(); j++){ // scorro gli elementi della lista originale
                if(db.getAccounts().get(j).getNatura() == Natures.RICAVO &&
                                db.getAccounts().get(j).getSezione()==Sections.valueOf(Integer.toString(i))){
                    Ricavi += "\n" + db.getAccounts().get(j).getName();
                }
            }
            Ricavi+="\n <b>"+ Sections.valueOf(Integer.toString(i+1))+ "</B>\n";
        }
        return Ricavi;
    }

    @Override
    public String Saldi_Ricavi() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Float getSaldo_Stato_Patr() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Float getSaldo_Conto_Ec() {
        // TODO Auto-generated method stub
        return null;
    }

}
