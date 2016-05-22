/**
 * 
 */
package controller.dbController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import dataModel.Company;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import view.AbstractFrame;

/**
 * @author Pentolo
 *
 */
public class DBSaver extends AbstractDB {

	private static void save(final boolean mustbeSaved, final File file,
			final LinkedList<? extends IDataTableModel> linkedList) throws IOException {
		boolean save = mustbeSaved;
		if (file.getParentFile().mkdir()) {
			save = true;
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IOException("Errore critico di creazione database. " + e.getMessage());
			}
			save = true;
		}
		if (save) {
			try (ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)))) {
				out.writeObject(linkedList);
			} catch (IOException e) {
				throw new IOException("Errore critico di scrittura. " + e.getMessage());
			}
		}
	}

	public static void saveCompanys(final LinkedList<Company> companys) throws IOException {
		save(true, getCompanyFile(), companys);
	}

	public DBSaver(final String path, final AbstractFrame view, final DBDataModel db) {
		super(path, view, db);
	}

	@Override
	public void run() {
		final DBDataModel db = getDb();
		try {
			save(db.isAccountsModified(), getAccountFile(), db.getAccounts());
			save(db.isCustomersSuppliersModified(), getCustomersupplierFile(), db.getCustomersSuppliers());
			save(db.isMovimentsModified(), getMovementFile(), db.getMoviments());
			save(db.isProductsModified(), getProductFile(), db.getProducts());
		} catch (IOException e) {
			getView().errorDialog("Errore di Scrittura", e.getMessage());
		}
		db.resetBooleans();
	}
}
