package model;

import java.util.Date;
import java.util.List;

public interface AnagraficaMovimenti {
	abstract void add(dataModel.Movimento mov);
	abstract void remove(dataModel.Movimento mov);
	abstract void edit(dataModel.Movimento mov);
	abstract Object search(dataModel.Movimento mov);
	abstract void close();
	
	public void aggiornaConti(List<dataModel.Conto> lista);
	public List<Object> getListaMovimenti(Date data);
	
}
