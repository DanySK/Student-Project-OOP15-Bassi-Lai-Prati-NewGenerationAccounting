package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dataEnum.Natures;
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

	private Map<Natures, Set<Account>> AccountEconomico;
	private Map<Natures, Set<Account>> StatoPatrimoniale;

	public FinancialSituationModelImpl(DBDataModel db) {

		this.StatoPatrimoniale = new HashMap<>();
		this.AccountEconomico = new HashMap<>();
	}

	private String analisiFinanz() {
		return null;
	}

	private Map<Natures, Set<Account>> calcolaCE() {
		return null;
	}

	private Map<Natures, Set<Account>> calcolaSP() {
		return null;
	}

	@Override
	public String getAnalisiFinanziaria() {
		// TODO Auto-generated method stub
		/*
		 * MARGINE DI STRUTTURA MS = (PATRIMONIO NETTO - ATTIVO a M/L TERMINE o
		 * IMMOBILIZZATO) Se il margine è positivo significa che il capitale
		 * proprio (capitale acquisito dall’impresa o che permane
		 * nell’impresa a titolo di pieno rischio) copre tutto il fabbisogno
		 * durevole; se negativo significa che parte del fabbisogno è coperta
		 * da debiti.
		 *
		 * MARGINE DI TESORERIA MT = (LIQUIDITÀ DIFFERITE +LIQUIDITÀ
		 * IMMEDIATE) - PASSIVITÀ a BREVE Il margine di tesoreria dovrebbe
		 * essere positivo. Se il margine è negativo significa che l’impresa
		 * si trova in zona di tensione finanziaria a breve termine perché, di
		 * fronte ad una richiesta di rimborso immediato di tutti i debiti a
		 * breve termine, non avrebbe i mezzi finanziari necessari per farvi
		 * fronte.
		 *
		 * CAPITALE CIRCOLARE NETTO FINANZIARIO CCN F = ATTIVITA’ A BREVE -
		 * PASSIVITÀ A BREVE Il capitale circolante netto finanziario deve non
		 * solo essere positivo, ma lo deve essere in misura adeguata. Se il
		 * margine è negativo significa che impieghi durevoli sono finanziati
		 * da passività a breve termine con riflessi preoccupanti sulla
		 * solvibilità.
		 *
		 * CAPITALE CIRCOLANTE NETTO OPERATIVO CCN O = ATTIVITA’ DELLA
		 * GESTIONE CARATTERISTICA CORRENTE - PASSIVITÀ DELLA GESTIONE CORRENTE
		 * Se il capitale circolante netto operativo è positivo significa che
		 * parte degli investimenti della gestione caratteristica corrente è
		 * finanziata dalla gestione medesima, attraverso il differimento delle
		 * uscite monetarie connesse ad alcuni costi della gestione
		 * caratteristica corrente.
		 */
		return null;
	}

	@Override
	public Map<Natures, Set<Account>> getContoEconomico() {
		return AccountEconomico;
		// TODO Auto-generated method stub
	}

	@Override
	public Map<Natures, Set<Account>> getStatoPatrimoniale() {
		return StatoPatrimoniale;
		// TODO Auto-generated method stub

	}

	public DBDataModel saveDBAndClose() {
		// TODO Auto-generated method stub
		return null;
	}

}
