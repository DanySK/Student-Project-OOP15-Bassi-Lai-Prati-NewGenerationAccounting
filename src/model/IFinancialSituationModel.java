package model;

import java.util.Map;
import java.util.Set;

import dataEnum.Natures;
import dataModel.Account;

/**
 * interfaccia delle operazioni per la destione di stato patrimoniale conto
 * economico e analisi per margini e indici
 * 
 * @author niky
 *
 */
public interface IFinancialSituationModel {
	public String getAnalisiFinanziaria();

	public Map<Natures, Set<Account>> getContoEconomico();

	public Map<Natures, Set<Account>> getStatoPatrimoniale();
}
