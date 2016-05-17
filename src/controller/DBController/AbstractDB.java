/**
 * 
 */
package controller.DBController;

import dataModel.DBDataModel;
import view.AbstractFrame;

/**
 * @author Pentolo
 *
 */
public abstract class AbstractDB extends Thread {

	private static final String SEPARATOR = "path.separator";
	private static final String DB_PATH = "user.home" + SEPARATOR + "NewGenerationAccounting" + SEPARATOR;
	private static final String ACCOUNT_FILENAME = "accounts.nga";
	private static final String COMPANY_FILENAME = "companys.nga";
	private static final String CUSTOMERSUPPLIER_FILENAME = "customersuppliers.nga";
	private static final String MOVEMENT_FILENAME = "movements.nga";
	private static final String PRODUCT_FILENAME = "products.nga";
	private final String path;
	private final AbstractFrame view;
	private final DBDataModel db;

	/**
	 * @return the path
	 */
	protected final String getPath() {
		return path;
	}

	/**
	 * @return the view
	 */
	protected final AbstractFrame getView() {
		return view;
	}

	/**
	 * @return the db
	 */
	protected final DBDataModel getDb() {
		return db;
	}

	/**
	 * 
	 */
	public AbstractDB(final String path, final AbstractFrame view, final DBDataModel db) {
		this.path = path;
		this.view = view;
		this.db = db;
	}

	/**
	 * @return the accountFilename
	 */
	protected static String getAccountFilename() {
		return ACCOUNT_FILENAME;
	}

	/**
	 * @return the companyFilename
	 */
	protected static final String getCompanyFilename() {
		return COMPANY_FILENAME;
	}

	/**
	 * @return the customersupplierFilename
	 */
	protected static final String getCustomersupplierFilename() {
		return CUSTOMERSUPPLIER_FILENAME;
	}

	/**
	 * @return the movementFilename
	 */
	protected static final String getMovementFilename() {
		return MOVEMENT_FILENAME;
	}

	/**
	 * @return the productFilename
	 */
	protected static final String getProductFilename() {
		return PRODUCT_FILENAME;
	}

	/**
	 * @return the separator
	 */
	protected static String getSeparator() {
		return SEPARATOR;
	}

	/**
	 * @return the dbPath
	 */
	protected static String getDbPath() {
		return DB_PATH;
	}
}
