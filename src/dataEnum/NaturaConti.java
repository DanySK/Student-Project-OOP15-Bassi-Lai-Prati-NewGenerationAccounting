package dataEnum;

public enum NaturaConti {

	ATT_ATTIVITA_FINANZ,
	// ATT = ATTIVITA' PASS = PAVVIVITA' -> STATO PATRIMONIALE
	ATT_CRED_VS_SOCI, // conterrà partecipazioni e altri proventi finanziari
	ATT_CREDITI, ATT_DISP_LIQU, ATT_IMMO_FINANZIARIE, ATT_IMMO_IMMATERIALI, ATT_IMMO_MATERIALI, ATT_RATEI_RISCONTI, ATT_RIMANENZE, C_ACCANTONAMENTI, C_AMMORTAMENTI_SVALUTAZIONI, C_BENI_DI_TERZI, // conterrà
																																																	// interessi
																																																	// e
																																																	// utili+perdite
																																																	// su
																																																	// cambi
	C_IMPOSTE, C_MATERIE, C_ONERI_DI_GESTIONE,

	C_ONERI_FINANZIARI, C_ONERI_STRAORD, C_PERSONALE, C_SERVIZI, C_SVALUTAZIONI, PASS_DEBITI, PASS_FONDI, PASS_PATR_NETTO, PASS_RATEI_RISCONTI, PASS_TFR, R_PROVENTI_FINANZIARI, R_PROVENTI_STRAORD, R_RIVALUTAZIONI,
	// R=RICAVI C= COSTI -> CONTO ECONOMICO
	R_VENDITE_PRESTAZ
}
