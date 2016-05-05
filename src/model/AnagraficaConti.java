package model;

public interface AnagraficaConti{
	abstract void add(dataModel.Conto conto);
	abstract void remove(dataModel.Conto conto);
	abstract void edit(dataModel.Conto conto);
	abstract Object search(dataModel.Conto conto);
	abstract void close();
	
	public void aggiornaConto(dataModel.Conto contoAggiornato);
}
