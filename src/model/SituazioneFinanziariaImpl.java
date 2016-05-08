package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dataEnum.Nature;
import dataModel.Conto;

public class SituazioneFinanziariaImpl implements IsituazioneFinanziaria{

    private Map<Nature,Set<Conto>> StatoPatrimoniale;
    private Map<Nature,Set<Conto>> ContoEconomico;
    
    public SituazioneFinanziariaImpl() {
        
        this.StatoPatrimoniale = new HashMap<>();
        this.ContoEconomico = new HashMap<>();
    }

    @Override
    public Map<Nature,Set<Conto>> getStatoPatrimoniale() {
        return StatoPatrimoniale;
        // TODO Auto-generated method stub
        
    }

    @Override
    public Map<Nature,Set<Conto>> getContoEconomico() {
        return ContoEconomico;
        // TODO Auto-generated method stub 
    }

    @Override
    public String getAnalisiFinanziaria() {
        // TODO Auto-generated method stub
        /*MARGINE DI STRUTTURA MS = (PATRIMONIO NETTO - ATTIVO a M/L TERMINE o IMMOBILIZZATO)
         * Se il margine è positivo significa che il capitale proprio
         *(capitale acquisito dall’impresa o che permane nell’impresa a titolo di pieno rischio)
         *copre tutto il fabbisogno durevole; se negativo significa che parte del fabbisogno è coperta da debiti.
         *
         *MARGINE DI TESORERIA MT = (LIQUIDITÀ DIFFERITE +LIQUIDITÀ IMMEDIATE) - PASSIVITÀ a BREVE
         *Il margine di tesoreria dovrebbe essere positivo. Se il margine è negativo significa che l’impresa si
         *trova in zona di tensione finanziaria a breve termine perché, di fronte ad una richiesta di rimborso
         *immediato di tutti i debiti a breve termine, non avrebbe i mezzi finanziari necessari per farvi fronte.
         *
         *CAPITALE CIRCOLARE NETTO FINANZIARIO CCN F = ATTIVITA’ A BREVE - PASSIVITÀ A BREVE
         *Il capitale circolante netto finanziario deve non solo essere positivo, ma lo deve essere in misura adeguata.
         *Se il margine è negativo significa che impieghi durevoli sono finanziati da passività a breve termine
         *con riflessi preoccupanti sulla solvibilità.
         *
         *CAPITALE CIRCOLANTE NETTO OPERATIVO CCN O = ATTIVITA’ DELLA GESTIONE CARATTERISTICA CORRENTE - PASSIVITÀ
         *DELLA GESTIONE CORRENTE Se il capitale circolante netto operativo è positivo 
         *significa che parte degli investimenti della gestione caratteristica corrente è finanziata dalla gestione 
         *medesima, attraverso il differimento delle uscite monetarie connesse ad alcuni costi della gestione 
         *caratteristica corrente.*/
        return null;
    }
    
    private Map<Nature,Set<Conto>> calcolaSP(){
        return null;
    }
    
    private Map<Nature,Set<Conto>> calcolaCE(){
        return null;
    }
    
    private String analisiFinanz(){
        return null;
    }
    
    

}
