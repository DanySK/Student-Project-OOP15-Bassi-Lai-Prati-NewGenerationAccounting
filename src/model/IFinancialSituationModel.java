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

	public LinkedList<Account> getCE();

	public LinkedList<Account> getSP();

}
