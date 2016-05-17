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

import dataModel.Company;
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
		try {
			save(db.isAccountsModified(), getPath() + getSeparator() + getAccountFilename(), db.getAccounts());
			save(db.isCustomersSuppliersModified(), getPath() + getSeparator() + getCustomersupplierFilename(),
					db.getCustomersSuppliers());
			save(db.isMovimentsModified(), getPath() + getSeparator() + getMovementFilename(), db.getMoviments());
			save(db.isProductsModified(), getPath() + getSeparator() + getProductFilename(), db.getProducts());
		} catch (IOException e) {
			getView().errorDialog("Errore di Scrittura", e.getMessage());
		}
		db.resetBooleans();
	}

	/**
	 * saves the linked list.
	 * 
	 * @param mustbeSaved
	 *            show if must be saved or must check existance
	 * @param fileName
	 *            the file name
	 * @param linkedList
	 *            the linked list that must be saved
	 * @throws IOException
	 */
	private static void save(final boolean mustbeSaved, final String filePath,
			final LinkedList<? extends IDataTableModel> linkedList) throws IOException {
		boolean save = mustbeSaved;
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IOException("Errore critico di creazione database.");
			}
			save = true;
		}
		if (save) {
			try (ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)))) {
				out.writeObject(linkedList);
			} catch (IOException e) {
				throw new IOException("Errore critico di scrittura.");
			}
		}
	}

	public static void saveCompanys(final LinkedList<Company> companys) throws IOException {
		save(true, getDbPath() + getCompanyFilename(), companys);
	}
}
