package dataModel;
/**
 * classe per gestire il singolo movimento in partita doppia
 * 
 * @author niky
 */
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Movement {

    private Date data;
    private List<Account> listaConti;

    public Movement(Date data, List<Account> lista) {
        this.data = data;
        this.listaConti = new LinkedList<Account>(lista);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Account> getListaConti() {
        return listaConti;
    }

    public void setListaConti(List<Account> listaConti) {
        this.listaConti = listaConti;
    }
    
}
