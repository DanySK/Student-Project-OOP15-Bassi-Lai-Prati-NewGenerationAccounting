/**
 * 
 */
package controller.dbController;

import java.io.File;

import dataModel.DBDataModel;
import view.AbstractFrame;

/**
 * @author Pentolo
 *
 */
public abstract class AbstractDB extends Thread {

	private static final String SEPARATOR = "path.separator";
	private static final String DB_PATH = "user.home" + SEPARATOR + "NewGenerationAccounting";
	private static final String ACCOUNT_FILENAME = "accounts.nga";
	private static final String COMPANY_FILENAME = "companys.nga";
	private static final String CUSTOMERSUPPLIER_FILENAME = "customersuppliers.nga";
	private static final String MOVEMENT_FILENAME = "movements.nga";
	private static final String PRODUCT_FILENAME = "products.nga";
	private final String path;
	private final AbstractFrame view;
	private final DBDataModel db;

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
	
	private File getFile(String fileName){
		return new File(DB_PATH + SEPARATOR + path + SEPARATOR + fileName);
	}

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
	 * @return the companyFile
	 */
	protected static File getCompanyFile() {
		return new File(DB_PATH + SEPARATOR + COMPANY_FILENAME);
	}

	/**
	 * @return the customersupplierFile
	 */
	protected File getCustomersupplierFile() {
		return getFile(CUSTOMERSUPPLIER_FILENAME);
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
}
