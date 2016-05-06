package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dataEnum.Nature;
import dataModel.Conto;

public class SituazioneFinanziariaImpl implements ISituazioneFinanziaria{

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
