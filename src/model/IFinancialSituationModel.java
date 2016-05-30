package model;

import java.util.LinkedList;

import dataModel.Account;

/**
 * interfaccia delle operazioni per la destione di stato patrimoniale conto
 * economico e analisi per margini e indici
 * 
 * @author niky
 *
 */
public interface IFinancialSituationModel {

	public String AnalisiFinanziaria();
	
	public String Attivita();
	
	public String Saldi_Attivita();
	
	public String Passivita();
	
	public String Saldi_Passivita();
	
	public String Costi();
	
	public String Saldi_Costi();
	
	public String Ricavi();
	
	public String Saldi_Ricavi();
	
	public Float getSaldo_Stato_Patr();
	
	public Float getSaldo_Conto_Ec();
	

}
