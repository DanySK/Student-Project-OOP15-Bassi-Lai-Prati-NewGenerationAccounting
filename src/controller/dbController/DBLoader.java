/**
 * 
 */
package controller.dbController;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import dataModel.Company;
import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import dataModel.Movement;
import dataModel.Product;
import model.AccountsModel;
import view.AbstractFrame;

/**
 * @author Pentolo
 *
 */
public class DBLoader extends AbstractDB {

	public static LinkedList<Company> loadCompanys() throws IOException {
		File file = getCompanyFile();
		file.getParentFile().mkdir();
		if (!file.exists()) {
			try {
				file.createNewFile();
				DBSaver.saveCompanys(new LinkedList<Company>());
			} catch (IOException e) {
				throw new IOException("Errore critico di lettura del database. " + e.getMessage());
			}
		}
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			Object readElem = ois.readObject();
			if (readElem instanceof java.util.LinkedList) {
				return (LinkedList<Company>) readElem;
			}
		} catch (Exception e) {
			throw new IOException("Errore di lettura. " + e.getMessage());
		}
		return new LinkedList<Company>();
	}

	public static DBDataModel loadDB(final String path, final AbstractFrame view) {
		DBDataModel db = new DBDataModel(path);
		new DBLoader(path, view, db).start();
		return db;
	}

	/**
	 * 
	 */
	private DBLoader(final String path, final AbstractFrame view, final DBDataModel db) {
		super(path, view, db);
	}

	@Override
	public void run() {
		DBDataModel db = getDb();
		db.setAccounts(load(getAccountFile(), AccountsModel.chartOfAccounts()));
		db.setCustomersSuppliers(load(getCustomersupplierFile(), new LinkedList<Customers_Suppliers>()));
		db.setMoviments(load(getMovementFile(), new LinkedList<Movement>()));
		db.setProducts(load(getProductFile(), new LinkedList<Product>()));
	}

	private LinkedList load(File file, LinkedList defaultList) {
		file.getParentFile().mkdir();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				getView().errorDialog("Errore di IO",
						"Errore critico di accesso al file del database. " + e.getMessage());
				return defaultList;
			}
		}
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			Object readElem = ois.readObject();
			if (!readElem.equals(null) && readElem instanceof java.util.LinkedList) {
				return (LinkedList) readElem;
			}
		} catch (Exception e) {
			getView().errorDialog("Errore di IO",
					"Errore critico di lettura del database. Il database verrà ripristinato allo stato iniziale.");
			return defaultList;
		}
		return defaultList;
	}
}
