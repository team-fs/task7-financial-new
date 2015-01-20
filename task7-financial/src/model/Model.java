package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.FundBean;
import databeans.PositionBean;
import databeans.PriceBean;
import databeans.TransactionBean;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO  employeeDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private PriceDAO priceDAO;
	private TransactionDAO transactionDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			customerDAO  = new CustomerDAO("customer", pool);
			employeeDAO = new EmployeeDAO("employee", pool);
			
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public FavoriteDAO getFavoriteDAO() { return favoriteDAO; }
	public UserDAO  getUserDAO()  { return userDAO;  }
}

