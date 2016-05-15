/**
 * 
 */
package dataModel;

import java.util.LinkedList;

/**
 * il database che conterrà tutte le informazioni del programma.
 * 
 * @author Pentolo
 *
 */
public class DBDataModel {

	private LinkedList<Account> accounts;
	private LinkedList<Company> companys;
	private LinkedList<Customers_Suppliers> customersSuppliers;
	private LinkedList<Movement> moviments;
	private LinkedList<Product> products;
	private boolean isAccountsModified = false;
	private boolean isCompanysModified = false;
	private boolean isCustomersSuppliersModified = false;
	private boolean isMovimentsModified = false;
	private boolean isProductsModified = false;
	
	public DBDataModel(final LinkedList<Account> accounts, final LinkedList<Company> companys,
			final LinkedList<Customers_Suppliers> customersSuppliers, final LinkedList<Movement> moviments,
			final LinkedList<Product> products) {
		super();
		this.accounts = accounts;
		this.companys = companys;
		this.customersSuppliers = customersSuppliers;
		this.moviments = moviments;
		this.products = products;
	}
	
	/**
	 * @return the accounts
	 */
	public LinkedList<Account> getAccounts() {
		return new LinkedList<Account>(accounts);
	}
	/**
	 * @return the companys
	 */
	public LinkedList<Company> getCompanys() {
		return new LinkedList<Company>(companys);
	}
	/**
	 * @return the customersSuppliers
	 */
	public LinkedList<Customers_Suppliers> getCustomersSuppliers() {
		return new LinkedList<Customers_Suppliers>(customersSuppliers);
	}
	/**
	 * @return the moviments
	 */
	public LinkedList<Movement> getMoviments() {
		return new LinkedList<Movement>(moviments);
	}
	/**
	 * @return the products
	 */
	public LinkedList<Product> getProducts() {
		return new LinkedList<Product>(products);
	}
	
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
	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(LinkedList<Account> accounts) {
		this.accounts = accounts;
		 isAccountsModified = true;
	}
	/**
	 * @param companys the companys to set
	 */
	public void setCompanys(LinkedList<Company> companys) {
		this.companys = companys;
		isCompanysModified = true;
	}
	/**
	 * @param customersSuppliers the customersSuppliers to set
	 */
	public void setCustomersSuppliers(LinkedList<Customers_Suppliers> customersSuppliers) {
		this.customersSuppliers = customersSuppliers;
		isCustomersSuppliersModified = true;
	}
	/**
	 * @param moviments the moviments to set
	 */
	public void setMoviments(LinkedList<Movement> moviments) {
		this.moviments = moviments;
		isCustomersSuppliersModified = true;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(LinkedList<Product> products) {
		this.products = products;
		isCustomersSuppliersModified = true;
	}
}
