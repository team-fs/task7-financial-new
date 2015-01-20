package model;

import databeans.EmployeeBean;

import org.genericdao.*;

public class EmployeeDAO extends GenericDAO<EmployeeBean>{
	
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(EmployeeBean.class, tableName, cp);
	}
	
	public void setPassword(String username, String password) throws RollbackException {
        try {
        	Transaction.begin();
			EmployeeBean employee = read(username);
			
			if (employee == null) {
				throw new RollbackException("User "+username+" no longer exists");
			}
			
			employee.setPassword(password);
			
			update(employee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

}
