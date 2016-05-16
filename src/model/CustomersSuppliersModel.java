package model;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.Map;

import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;

public class CustomersSuppliersModel extends AbstractModel {
    
    DBDataModel db;
    public CustomersSuppliersModel(DBDataModel db) {
        this.db = db;
    }
	@Override
	public LinkedList<Customers_Suppliers> load() {

		return new LinkedList<>();
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
