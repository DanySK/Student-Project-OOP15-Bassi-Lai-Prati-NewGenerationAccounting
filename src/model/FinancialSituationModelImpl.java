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

    private LinkedList<Account> SP;
    private LinkedList<Account> CE;
    private DBDataModel db;
    private LinkedList<Account> contiRegistrati = db.getAccounts();
    private static String ROI;
    private static String ROS;
    private static String leverage;
    private static String ROT;
    private static String ROE;
    private static String margineStrutturaPrimario;
    private static String margineStrutturaSecondario;
    private static String margineDiTesoreria;

	public FinancialSituationModelImpl(DBDataModel db) {
		this.db = db;
		SP = new LinkedList<Account>();
		CE = new LinkedList<Account>();
	}

	private String analisiFinanziaria() {
		return null;
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

	@Override
	public LinkedList<Account> getSP() {
		return calcolaSP();
	}

	public DBDataModel saveDBAndClose() {
		// TODO Auto-generated method stub
		return null;
	}
	    private Map<String,Float>calcolaIndici_Margini(){
	        Map<String,Float> indiciMargini = new HashMap<>();
	        // calcolo dell'indice ROS
	        // calcolo dell'indice ROT
	        // calcolo dell'indice ROI
	        // calcolo dell'indice leverage
	        // calcolo dell'indice ROE
	        // calcolo del margine di struttura primario
	        // calcolo del margine di struttura secondario
	        // calcolo del margine di tesoreria
	        return null;
	        
	    }

        @Override
        public String getAnalisiFinanziaria() {
            // TODO Auto-generated method stub
            return null;
        }

}
