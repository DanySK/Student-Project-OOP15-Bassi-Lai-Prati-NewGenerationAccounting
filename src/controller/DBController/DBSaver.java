/**
 * 
 */
package controller.DBController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import view.AbstractFrame;

/**
 * @author Pentolo
 *
 */
public class DBSaver extends AbstractDB {

	public DBSaver(final String path, final AbstractFrame view, final DBDataModel db) {
		super(path, view, db);
	}

	@Override
	public void run() {
		final DBDataModel db = getDb();
		save(db.isAccountsModified(), getAccountFilename(), db.getAccounts());
		save(db.isCompanysModified(), getCompanyFilename(), db.getCompanys());
		save(db.isCustomersSuppliersModified(), getCustomersupplierFilename(), db.getCustomersSuppliers());
		save(db.isMovimentsModified(), getMovementFilename(), db.getMoviments());
		save(db.isProductsModified(), getProductFilename(), db.getProducts());
		db.resetBooleans();
	}

	private void save(final boolean mustbeSaved, final String fileName,
			final LinkedList<? extends IDataTableModel> linkedList) {
		boolean save = mustbeSaved;
		File file = new File(getPath() + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				showError("Errore critico di creazione database.");
				return;
			}
			save = true;
		}
		if (save) {
			try (ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));) {
				out.writeObject(linkedList);
				out.close();
			} catch (IOException e) {
				showError("Errore critico di scrittura.");
				return;
			}
		}
	}

	private void showError(String string) {
		getView().errorDialog("Errore", string);
	}
}
