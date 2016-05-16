package model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dataEnum.Gender;
import dataEnum.KindPerson;
import dataModel.Company;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

/**
 * Classe implementativa per la gestione dell'anagrafica aziende
 * 
 * @author Diego
 *
 */

public class CompanyModel extends AbstractModel {
	
	public List<Company> listaaziende;
	
	private Company nuovo;
	private Company elem;
	
    DBDataModel db;
    public CompanyModel(DBDataModel db) {
        this.db = db;
    }
	@Override
	public LinkedList<Company> load() {
		return new LinkedList<>(Arrays.asList(new Company(1, "password", "societï¿½ 1", 123456789, "via dalle palle, 3",
				"cittadimmerda", 11111, "Levati (dal cazzo)", "1100110011")));
	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removeElem(Map<String, Object> elemDaEliminare) throws ParseException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addElem(Map<String, Object> elem) throws ParseException {
	
		nuovo.setCap((int) elem.get("Cap"));
		nuovo.setCitta(elem.get("Città").toString());
		nuovo.setCodice_azienda((int) elem.get("Città"));
		nuovo.setIndirizzo(elem.get("Indirizzo").toString());
		nuovo.setPartita_iva((int) elem.get("P.IVA"));
		nuovo.setPassword(elem.get("Password").toString());
		nuovo.setProvincia(elem.get("Provincia").toString());
		nuovo.setRagione_sociale(elem.get("Ragione Sociale").toString());
		nuovo.setTel(elem.get("Telefono").toString());
		 if(listaaziende.contains(nuovo)){
	            System.out.println("Azienda gia' registrata");
	        }
	        else listaaziende.add(nuovo);
		
		
	}

}
