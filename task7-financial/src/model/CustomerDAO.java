package model;

import databeans.CustomerBean;

import org.genericdao.*;

public class CustomerDAO extends GenericDAO<CustomerBean>{

	public CustomerDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CustomerBean.class, tableName, pool);
	}
	
	public void setPassword(String username, String password) throws RollbackException {
        try {
        	Transaction.begin();
			CustomerBean customer = read(username);
			
			if (customer == null) {
				throw new RollbackException("User "+username+" no longer exists");
			}
			
			customer.setPassword(password);
			
			update(customer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
