/**
 * 
 */
package controller.dbController;

import java.io.File;

import dataModel.DBDataModel;
import view.AbstractFrame;

/**
 * classe astratta per tutte le classi che interagiranno con il DataBase
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractDB extends Thread {

	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String DB_PATH = System.getProperty("user.dir") + SEPARATOR + "NewGenerationAccounting";
	private static final String ACCOUNT_FILENAME = "accounts.nga";
	private static final String COMPANY_FILENAME = "companys.nga";
	private static final String CUSTOMERSUPPLIER_FILENAME = "customersuppliers.nga";
	private static final String MOVEMENT_FILENAME = "movements.nga";
	private static final String PRODUCT_FILENAME = "products.nga";

	/**
	 * @return the companyFile
	 */
	protected static File getCompanyFile() {
		return new File(DB_PATH + SEPARATOR + COMPANY_FILENAME);
	}

	private final String path;
	private final AbstractFrame view;

	private final DBDataModel db;

	/**
	 * 
	 */
	protected AbstractDB(final String path, final AbstractFrame view, final DBDataModel db) {
		this.path = path;
		this.view = view;
		this.db = db;
	}

	/**
	 * @return the accountFile
	 */
	protected File getAccountFile() {
		return getFile(ACCOUNT_FILENAME);
	}

	/**
	 * @return the customersupplierFile
	 */
	protected File getCustomersupplierFile() {
		return getFile(CUSTOMERSUPPLIER_FILENAME);
	}

	protected static File getDBDirectory(String path) {
		return new File(DB_PATH + SEPARATOR + path);
	}

	/**
	 * @return the db
	 */
	protected final DBDataModel getDb() {
		return db;
	}

	private File getFile(String fileName) {
		return new File(DB_PATH + SEPARATOR + path + SEPARATOR + fileName);
	}

	/**
	 * @return the movementFile
	 */
	protected File getMovementFile() {
		return getFile(MOVEMENT_FILENAME);
	}

	/**
	 * @return the productFile
	 */
	protected File getProductFile() {
		return getFile(PRODUCT_FILENAME);
	}

	/**
	 * @return the view
	 */
	protected final AbstractFrame getView() {
		return view;
	}
}
