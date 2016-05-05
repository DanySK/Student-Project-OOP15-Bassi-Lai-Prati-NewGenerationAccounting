package dataModel;

import java.util.Date;
import java.util.List;

public class Movimento {

    private Date data;
    private List<Conto> listaConti;
    
	public Movimento(List<Conto> listaConti,Date data) {
		this.listaConti = listaConti;
		this.data = data;
	}

    public Movimento(Date data, String nomeConto, String dare, String avere) {
        this.data = data;
        /*
        this.listaConti.add(nomeConto);
        this.listaConti.add(dare);
        this.listaConti.add(avere);
        */
    }

    public Date getData() {
        return data;
    }

	public List<Conto> getListaConti() {
		return listaConti;
	}
    public void setData(Date data) {
        this.data = data;
    }
	public void setListaConti(List<Conto> listaConti) {
		this.listaConti = listaConti;
	}
	
    public List<Conto> getInfoMovimento() {
        return listaConti;
    }

    public void setInfoMovimento(List<Conto> infoMovimento) {
        this.listaConti = infoMovimento;
    }
}
