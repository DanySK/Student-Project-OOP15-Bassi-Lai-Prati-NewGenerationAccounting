package dataModel;

import java.util.Date;
import java.util.List;

public class Movimento {
    private Date data;
    private List<String> infoMovimento;

    public Movimento(Date data, String nomeConto, String dare, String avere) {
        this.data = data;
        this.infoMovimento.add(nomeConto);
        this.infoMovimento.add(dare);
        this.infoMovimento.add(avere);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<String> getInfoMovimento() {
        return infoMovimento;
    }

    public void setInfoMovimento(List<String> infoMovimento) {
        this.infoMovimento = infoMovimento;
    }

}
