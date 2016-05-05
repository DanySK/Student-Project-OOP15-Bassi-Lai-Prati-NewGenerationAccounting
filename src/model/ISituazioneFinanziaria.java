package model;

import java.util.Map;
import java.util.Set;

import dataEnum.Nature;
import dataModel.Conto;

public interface ISituazioneFinanziaria {
	// qui ci saranno stato patrimoniale, conto economico e
	// analisi per indici e margini con relativo commento
    
    public Map<Nature, Set<Conto>> getStatoPatrimoniale();
    
    public Map<Nature, Set<Conto>> getContoEconomico();
    
    public String getAnalisiFinanziaria();
    
}
