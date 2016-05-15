/**
 * 
 */
package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import dataModel.DBDataModel;
import view.AbstractFrame;

/**
 * @author Pentolo
 *
 */
public class DBSaver extends Thread {

	private static final String ACCOUNT_FILENAME = "accounts.nga";
	private static final String COMPANY_FILENAME = "companys.nga";
	private static final String CUSTOMERSUPPLIER_FILENAME = "customersuppliers.nga";
	private static final String MOVEMENT_FILENAME = "movements.nga";
	private static final String PRODUCT_FILENAME = "products.nga";
	private final DBDataModel DB;
	private final String path;
	private final AbstractFrame view;
	
	public DBSaver(DBDataModel dB, String path, AbstractFrame view) {
		super();
		DB = dB;
		this.path = path;
		this.view = view;
	}

	@Override
	public void run() {
		save(DB.isAccountsModified(), ACCOUNT_FILENAME, DB.getAccounts());
		save(DB.isCompanysModified(), COMPANY_FILENAME, DB.getCompanys());
		save(DB.isCustomersSuppliersModified(), CUSTOMERSUPPLIER_FILENAME, DB.getCustomersSuppliers());
		save(DB.isMovimentsModified(), MOVEMENT_FILENAME, DB.getMoviments());
		save(DB.isProductsModified(), PRODUCT_FILENAME, DB.getProducts());
		DB.resetBooleans();
	}

	private void save(final boolean mustbeSaved, final String fileName, final LinkedList linkedList){
		boolean save = mustbeSaved;
		File file = new File(path + fileName);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				showError("Errore critico di creazione database.");
				return;
			}
			save = true;
		}
		if (save) {
			try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));){
				out.writeObject(linkedList);
				out.close();
			} catch (IOException e) {
				showError("Errore critico di scrittura.");
				return;
			}			
		}		
	}
	
	private void showError(String string){
		view.errorDialog("Errore", string);
	}
}
