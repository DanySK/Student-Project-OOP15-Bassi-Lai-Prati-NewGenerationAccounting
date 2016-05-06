package dataModel;

import java.util.Date;
import java.util.List;

public class Movimento {

    private Date data;
    private List<Conto> listaConti;

    public Movimento(Date data, Conto conto) {
        this.data = data;
        this.listaConti.add(conto);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Conto> getListaConti() {
        return listaConti;
    }

    public void setListaConti(List<Conto> listaConti) {
        this.listaConti = listaConti;
    }
    
}
