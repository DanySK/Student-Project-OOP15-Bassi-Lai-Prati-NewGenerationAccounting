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
public class DBLoader extends AbstractDB {

	/**
	 * 
	 */
	public DBLoader(final String path, final AbstractFrame view, final DBDataModel db) {
		super(path, view, db);
	}

	@Override
	public void run() {

	}

	public static DBDataModel LoadDB(final String path, final AbstractFrame view) {
		DBDataModel db = new DBDataModel();
		DBLoader dlb = new DBLoader(path, view, db);
		dlb.start();
		return db;
	}
}
