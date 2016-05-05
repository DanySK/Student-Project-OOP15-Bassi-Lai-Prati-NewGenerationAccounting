package dataModel;

import java.util.Date;
import java.util.List;

public class Movimento {
<<<<<<< local
	Date data;
	List<Conto> listaConti;

	public Movimento(List<Conto> listaConti,Date data) {
		this.listaConti = listaConti;
		this.data = data;
	}
=======
    private Date data;
    private List<String> infoMovimento;
>>>>>>> other

    public Movimento(Date data, String nomeConto, String dare, String avere) {
        this.data = data;
        this.infoMovimento.add(nomeConto);
        this.infoMovimento.add(dare);
        this.infoMovimento.add(avere);
    }

    public Date getData() {
        return data;
    }

<<<<<<< local
	public List<Conto> getListaConti() {
		return listaConti;
	}
=======
    public void setData(Date data) {
        this.data = data;
    }
>>>>>>> other

<<<<<<< local
	public void setListaConti(List<Conto> listaConti) {
		this.listaConti = listaConti;
	}
	
	
=======
    public List<String> getInfoMovimento() {
        return infoMovimento;
    }

    public void setInfoMovimento(List<String> infoMovimento) {
        this.infoMovimento = infoMovimento;
    }

>>>>>>> other
}
