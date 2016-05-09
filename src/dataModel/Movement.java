package dataModel;
/**
 * classe per gestire il singolo movimento in partita doppia
 * 
 * @author niky
 */
import java.util.Date;
import java.util.List;

public class Movement {

    private Date data;
    private List<Bill> listaConti;

    public Movement(Date data, Bill conto) {
        this.data = data;
        this.listaConti.add(conto);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Bill> getListaConti() {
        return listaConti;
    }

    public void setListaConti(List<Bill> listaConti) {
        this.listaConti = listaConti;
    }
    
}
