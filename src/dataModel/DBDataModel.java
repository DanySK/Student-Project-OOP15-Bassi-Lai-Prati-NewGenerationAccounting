/**
 * 
 */
package dataModel;

import java.util.LinkedList;

/**
 * @author Pentolo
 *
 */
public class DBDataModel {

	public DBDataModel(LinkedList<Account> accounts, LinkedList<Company> companys,
			LinkedList<Customers_Suppliers> customersSuppliers, LinkedList<Movement> moviments,
			LinkedList<Product> products) {
		super();
		this.accounts = accounts;
		this.companys = companys;
		this.customersSuppliers = customersSuppliers;
		this.moviments = moviments;
		this.products = products;
	}
	private LinkedList<Account> accounts;
	private LinkedList<Company> companys;
	private LinkedList<Customers_Suppliers> customersSuppliers;
	private LinkedList<Movement> moviments;
	/**
	 * @return the isAccountsModified
	 */
	public final boolean isAccountsModified() {
		return isAccountsModified;
	}

	/**
	 * @return the isCompanysModified
	 */
	public final boolean isCompanysModified() {
		return isCompanysModified;
	}

	/**
	 * @return the isCustomersSuppliersModified
	 */
	public final boolean isCustomersSuppliersModified() {
		return isCustomersSuppliersModified;
	}

	/**
	 * @return the isMovimentsModified
	 */
	public final boolean isMovimentsModified() {
		return isMovimentsModified;
	}

	/**
	 * @return the isProductsModified
	 */
	public final boolean isProductsModified() {
		return isProductsModified;
	}
	private LinkedList<Product> products;
	
	private boolean isAccountsModified = false;
	private boolean isCompanysModified = false;
	private boolean isCustomersSuppliersModified = false;
	private boolean isMovimentsModified = false;
	private boolean isProductsModified = false;
	
	/**
	 * @return the accounts
	 */
	public LinkedList<Account> getAccounts() {
		return accounts;
	}
	
	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(LinkedList<Account> accounts) {
		this.accounts = accounts;
		 isAccountsModified = true;
	}
	/**
	 * @return the companys
	 */
	public LinkedList<Company> getCompanys() {
		return companys;
	}
	/**
	 * @param companys the companys to set
	 */
	public void setCompanys(LinkedList<Company> companys) {
		this.companys = companys;
		isCompanysModified = true;
	}
	/**
	 * @return the customersSuppliers
	 */
	public LinkedList<Customers_Suppliers> getCustomersSuppliers() {
		return customersSuppliers;
	}
	/**
	 * @param customersSuppliers the customersSuppliers to set
	 */
	public void setCustomersSuppliers(LinkedList<Customers_Suppliers> customersSuppliers) {
		this.customersSuppliers = customersSuppliers;
		isCustomersSuppliersModified = true;
	}
	/**
	 * @return the moviments
	 */
	public LinkedList<Movement> getMoviments() {
		return moviments;
	}
	/**
	 * @param moviments the moviments to set
	 */
	public void setMoviments(LinkedList<Movement> moviments) {
		this.moviments = moviments;
		isCustomersSuppliersModified = true;
	}
	/**
	 * @return the products
	 */
	public LinkedList<Product> getProducts() {
		return products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(LinkedList<Product> products) {
		this.products = products;
		isCustomersSuppliersModified = true;
	}
	
	

}
