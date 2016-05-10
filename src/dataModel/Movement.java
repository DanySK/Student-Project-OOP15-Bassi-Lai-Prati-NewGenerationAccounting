package dataModel;
/**
 * classe per gestire il singolo movimento in partita doppia
 * 
 * @author niky
 */
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Movement implements IDataTableModel{

    private Date data;
    private List<Account> listaConti;

    public Movement(Date data, List<Account> lista) {
        this.data = data;
        this.listaConti = new LinkedList<Account>(lista);
    }
    private static final String[] intestazione = { "Data", "Nome" };

    public static String[] getIntestazione() {
            return intestazione;
    }
    @Override
    public String getValueAt(int column) {
        switch (column) {
        case 0:
                return getData().toString();
        case 1:
                for(Account conto : listaConti){
                    return conto.getName();
                }
        default:
                return "";
        }
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
