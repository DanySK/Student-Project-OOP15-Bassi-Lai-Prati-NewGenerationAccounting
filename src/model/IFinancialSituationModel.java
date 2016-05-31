package model;

/**
 * interfaccia delle operazioni per la destione di stato patrimoniale conto
 * economico e analisi per margini e indici
 * 
 * @author niky
 *
 */
public interface IFinancialSituationModel {

	/**
	 * funzione per commentare la situazione finanziaria derivata dal calcolo di
	 * margini e indici
	 * 
	 * @return il commento agli indici di bilancio
	 */
	public String AnalisiFinanziaria();

	/**
	 * funzione per popolare la colonna di nomi e sezioni dello stato
	 * patrimoniale dalla parte delle attività
	 * 
	 * @return la stringa da inserire nel prospetto dello stato patrimoniale per
	 *         le attività
	 */
	public String Attivita();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce dello stato patrimoniale dalla parte delle attività
	 * 
	 * @return la stringa con i saldi
	 */
	public String Saldi_Attivita();

	/**
	 * funzione per popolare la colonna di nomi e sezioni dello stato
	 * patrimoniale dalla parte delle passività
	 * 
	 * @return la stringa da inserire nel prospetto dello stato patrimoniale per
	 *         le passività
	 */
	public String Passivita();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce dello stato patrimoniale dalla parte delle passività
	 * 
	 * @return la stringa con i saldi
	 */
	public String Saldi_Passivita();

	/**
	 * funzione per popolare la colonna di nomi e sezioni del Conto Economico
	 * dalla parte dei Costi
	 * 
	 * @return la stringa da inserire nel prospetto del Conto Economico per i
	 *         Costi
	 */
	public String Costi();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce del Conto Economico dalla parte dei costi
	 * 
	 * @return la stringa con i saldi
	 */
	public String Saldi_Costi();

	/**
	 * funzione per popolare la colonna di nomi e sezioni del Conto Economico
	 * dalla parte dei Ricavi
	 * 
	 * @return la stringa da inserire nel prospetto del Conto Economico per i
	 *         Ricavi
	 */
	public String Ricavi();

	/**
	 * funzione per inserire saldi singoli e totali sezione al fianco di ogni
	 * voce del Conto Economico dalla parte dei Ricavi
	 * 
	 * @return la stringa con i saldi
	 */
	public String Saldi_Ricavi();

	/**
	 * funzione per calcolare il totale dello stato patrimoniale
	 * 
	 * @return la stringa con il saldo
	 */
	public Float getSaldo_Stato_Patr();

	/**
	 * funzione per calcolare il totale del Conto Economico
	 * 
	 * @return la stringa con il saldo
	 */
	public Float getSaldo_Conto_Ec();

}
