package model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataEnum.Natures;
import dataEnum.Sections;
import dataModel.Account;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Movement;
import dataModel.Operation;

/**
 * classe implementativa per la gestione dell'anagrafica dei movimenti
 * 
 * @author niky
 *
 */
public class MovementsModel extends AbstractModel {

	private final static String DATA = "Data Movimento";
	private final static String LISTA = "Lista Conti Mossi";
	private final static String DA = "Data da cui iniziare a cercare";
	private final static String A = "Data fino a cui cercare";
	private DBDataModel db;
	private LinkedList<Movement> listaMovimenti;
	private float temp;

	public MovementsModel(DBDataModel db) {
		this.db = db;
		listaMovimenti = db.getMoviments();
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws InstanceAlreadyExistsException, InstanceNotFoundException {
	    //controllare qui e nella edit che il movimento abbia il saldo dare e avere uguali
	    // una riga del movimento non può avere dare e avere insieme
	        if(!(elem.get(DATA) instanceof Date)){
	            throw new IllegalArgumentException("data inserita non valida");
	        }
	        if(!(elem.get(LISTA)instanceof LinkedList)){
	            throw new IllegalArgumentException("lista inserita non valida");
	        }
	        Movement m = new Movement((Date) elem.get(DATA), (LinkedList<Operation>) elem.get(LISTA));
		if (listaMovimenti.contains(m)) {
			throw new InstanceAlreadyExistsException("elemento già esistente");
		}
		listaMovimenti.add(m);
		LinkedList<Account> accountList = db.getAccounts();
		for (Operation op : m.getListaConti()) {
			if(accountList.contains(op.getConto())){
			    for(Account a : accountList){
			        if (a == op.getConto()){
			            if (a.getNatura() == Natures.ATTIVITA || a.getNatura() == Natures.COSTO){
			                a.incrSaldo(op.getDare());
			                a.decrSaldo(op.getAvere());
			            }
			            else if (a.getNatura() == Natures.PASSIVITA || a.getNatura() == Natures.RICAVO){
			                a.incrSaldo(op.getAvere());
			                a.decrSaldo(op.getDare());
			            }
			        }
			    }
			}
			else{
			    throw new InstanceNotFoundException("il conto cercato non è presente in lista");
			}
		}
		db.setAccounts(accountList);
	}

	@Override
	public void editElem(IDataTableModel obj, Map<String, Object> elemDaModificare)
			throws IllegalArgumentException, InstanceNotFoundException, InstanceAlreadyExistsException {
		if (obj instanceof Movement) {
		    for (Movement mov : listaMovimenti) {
				if (mov==obj) {
					remove(mov);
					addElem(elemDaModificare);
				}
			}
		} else {
			throw new IllegalArgumentException("l'oggetto passato non è un movimento");
		}
	}

	public LinkedList<Account> getAllAccounts() {
		LinkedList<Account> accounts = db.getAccounts();
		accounts.add(0, new Account("", Natures.ATTIVITA, Sections.IMMOBILIZZAZIONI_IMMATERIALI, 0));
		return accounts;
	}

	@Override
	public Map<String, Object> getFilterMap() {
		Map<String, Object> mappaFiltro = new HashMap<>();
		mappaFiltro.put(DA, new Date());
		mappaFiltro.put(A, new Date());
		return mappaFiltro;
	}

	@Override
	public Map<String, Object> getMap(IDataTableModel obj) {
		if (obj == null) {
			Map<String, Object> mappaVuota = new HashMap<>();
			mappaVuota.put(DATA, new Date());
			mappaVuota.put(LISTA, new LinkedList<Operation>());
			return mappaVuota;
		} else {
			if (obj instanceof Movement) {
				Map<String, Object> mappaPiena = new HashMap<>();
				mappaPiena.put(DATA, ((Movement) obj).getData());
				mappaPiena.put(LISTA, ((Movement) obj).getListaConti());
				return mappaPiena;
			} else
				throw new IllegalArgumentException("l'oggetto inserito non è un movimento");
		}
	}

	@Override
	public LinkedList<Movement> load() {
		return listaMovimenti;
	}

	@Override
	public LinkedList<Movement> load(Map<String, Object> mappaFiltro) {
		LinkedList<Movement> listaFiltrata = new LinkedList<>();
		Date da = (Date) mappaFiltro.get(DA);
		Date a = (Date) mappaFiltro.get(A);
		if (da != null && a != null) {
			for (Movement m : listaMovimenti) {
				if (m.getData().after(da) && m.getData().before(a)) {
					listaFiltrata.add(m);
				}
			}
		} else if (da != null && a == null) {
			for (Movement m : listaMovimenti) {
				if (m.getData().after(da)) {
					listaFiltrata.add(m);
				}
			}
		} else if (da == null && a != null) {
			for (Movement m : listaMovimenti) {
				if (m.getData().before(a)) {
					listaFiltrata.add(m);
				}
			}
		} else if (da == null && a == null) {
			throw new IllegalArgumentException("le date inserite non sono valide");
		}

		return listaFiltrata;
	}

	@Override
	public void remove(IDataTableModel elemDaEliminare) throws InstanceNotFoundException {
		AccountsModel a = new AccountsModel(db);
		if (elemDaEliminare instanceof Movement) {
			Movement m = (Movement) elemDaEliminare;
			if (m.getData() == null || m.getListaConti().isEmpty()) {
				throw new IllegalArgumentException("data non valida o lista vuota");
			} else {
				if (listaMovimenti.contains(m)) {
					listaMovimenti.remove(m);
					for (Operation op : m.getListaConti()) {
						temp = op.getAvere();
						op.setAvere(op.getDare());
						op.setDare(temp);
						a.updateAccounts(op);
					}
				} else {
					throw new InstanceNotFoundException("elemento da eliminarenon è presente in lista");
				}
			}
		} else {
			throw new IllegalArgumentException("l'oggetto da rimuovere non è un movimento");
		}
	}

	@Override
	public DBDataModel saveDBAndClose() {
		db.setMoviments(listaMovimenti);
		return db;
	}
}
