package model;

import java.util.LinkedList;
import java.util.Map;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Product;

public class ProductsModel extends AbstractModel {

	DBDataModel db;

	public ProductsModel(DBDataModel db) {
		this.db = db;
	}

	@Override
	protected void addElem(Map<String, Object> elem) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void editElem(IDataTableModel obj, Map<String, Object> ifoDaModificare) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Map<String, Object> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Product> load() {
		return new LinkedList<>();
	}

	@Override
	public void remove(IDataTableModel elem) {
		// TODO Auto-generated method stub

	}

	@Override
	public DBDataModel saveDBAndClose() {
		// TODO Auto-generated method stub
		return null;
	}

}
