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
	private LinkedList<Account> contiRegistrati = db.getAccounts();

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

	private Map<String, Float> calcolaIndici_Margini() {
		Map<String, Float> indiciMargini = new HashMap<>();
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
	public String getAnalisiFinanziaria() {
	    Map<String,Float> mappaIndiciMargini = new HashMap<>();
            mappaIndiciMargini = calcolaIndici_Margini();
            return null;
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
	
	private Float getROI(){
        return null;
	}
	
	private Float getROS(){
	    return null;
	}
	
	private Float getROT(){
            return null;
        }
	
	private Float getLeverage(){
            return null;
        }
	
	private Float getROE(){
            return null;
        }
	
	private Float getMargineStrutturaPrimario(){
            return null;
        }
	
	private Float getMargineStrutturaSecondario(){
            return null;
        }
	
	private Float getMargineTesoreria(){
            return null;
        }
	

}
