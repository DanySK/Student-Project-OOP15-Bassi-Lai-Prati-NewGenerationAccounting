package model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import dataModel.Company;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

public class CompanyModel extends AbstractModel {

    DBDataModel db;
    public CompanyModel(DBDataModel db) {
        this.db = db;
    }
	@Override
	public LinkedList<Company> load() {
		return new LinkedList<>(Arrays.asList(new Company(1, "password", "societ� 1", 123456789, "via dalle palle, 3",
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
		// TODO Auto-generated method stub

	}

}
