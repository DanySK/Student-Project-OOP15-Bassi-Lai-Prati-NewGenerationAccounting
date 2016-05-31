package controller.popup;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JComponent;

import dataModel.Account;

public interface IPopupController {

	void chiusura();

	void filterList(Map<String, Object> mappa);

	LinkedList<Account> getAccountsList();

	void go(HashMap<String, JComponent> compoMap);

	Map<String, Object> populateMap(HashMap<String, JComponent> compoMap);

}