package model;

import java.util.LinkedList;

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
	private float ROI = 0;
	private float ROS = 0;
	private float leverage = 0;
	private float ROT = 0;
	private float ROE = 0;
	private float margineStrutturaPrimario = 0;
	private float margineStrutturaSecondario = 0;
	private float margineDiTesoreria = 0;

	public FinancialSituationModelImpl(DBDataModel db) {
		this.db = db;
		SP = new LinkedList<Account>();
		CE = new LinkedList<Account>();
	}

	private String analisiFinanz() {
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

	private LinkedList<Account> calcolaSP() { // nature = attivita e passivita /
												// sezioni getAttivita e
												// getPassivita

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
		// TODO Auto-generated method stub
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

}
