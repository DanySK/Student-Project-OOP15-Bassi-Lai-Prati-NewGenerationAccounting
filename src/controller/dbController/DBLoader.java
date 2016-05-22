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
import dataModel.DBDataModel;
import view.AbstractFrame;

/**
 * @author Pentolo
 *
 */
public class DBLoader extends AbstractDB {

	/**
	 * 
	 */
	private DBLoader(final String path, final AbstractFrame view, final DBDataModel db) {
		super(path, view, db);
	}

	@Override
	public void run() {

	}

	public static DBDataModel loadDB(final String path, final AbstractFrame view) {
		DBDataModel db = new DBDataModel();
		new DBLoader(path, view, db).start();
		return db;
	}

	public static LinkedList<Company> loadCompanys() throws IOException {
		File file = getCompanyFile();
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
}
