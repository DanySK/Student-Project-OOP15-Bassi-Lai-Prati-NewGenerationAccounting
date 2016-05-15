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
	private boolean accountsModified = false;
	private boolean companysModified = false;
	private boolean customersSuppliersModified = false;
	private boolean movimentsModified = false;
	private boolean productsModified = false;

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
		return accountsModified;
	}

	/**
	 * @return the isCompanysModified
	 */
	public final boolean isCompanysModified() {
		return companysModified;
	}

	/**
	 * @return the isCustomersSuppliersModified
	 */
	public final boolean isCustomersSuppliersModified() {
		return customersSuppliersModified;
	}

	/**
	 * @return the isMovimentsModified
	 */
	public final boolean isMovimentsModified() {
		return movimentsModified;
	}

	/**
	 * @return the isProductsModified
	 */
	public final boolean isProductsModified() {
		return productsModified;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(LinkedList<Account> accounts) {
		this.accounts = accounts;
		accountsModified = true;
	}

	/**
	 * @param companys
	 *            the companys to set
	 */
	public void setCompanys(LinkedList<Company> companys) {
		this.companys = companys;
		companysModified = true;
	}

	/**
	 * @param customersSuppliers
	 *            the customersSuppliers to set
	 */
	public void setCustomersSuppliers(LinkedList<Customers_Suppliers> customersSuppliers) {
		this.customersSuppliers = customersSuppliers;
		customersSuppliersModified = true;
	}

	/**
	 * @param moviments
	 *            the moviments to set
	 */
	public void setMoviments(LinkedList<Movement> moviments) {
		this.moviments = moviments;
		customersSuppliersModified = true;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(LinkedList<Product> products) {
		this.products = products;
		customersSuppliersModified = true;
	}

	/**
	 * reset all the booleans of modified
	 */
	public void resetBooleans() {
		accountsModified = false;
		companysModified = false;
		customersSuppliersModified = false;
		movimentsModified = false;
		productsModified = false;
	}
}
