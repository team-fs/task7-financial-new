package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean>{
	
	public TransactionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(TransactionBean.class, tableName, pool);
	}
	
	public TransactionBean[] getTransactions (int customerId) {
	    try {
	    	Transaction.begin();
	    	
	    	TransactionBean[] transactions;
	    	transactions=match(MatchArg.equals("customer_id", customerId));
	    	//Need to sort??
	    	Transaction.commit();
			return transactions;
	    } catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
	    }
	    return null;
	}
	
	public void updateBalance(int id) throws RollbackException{
		try {
        	Transaction.begin();
			TransactionBean transaction = read(id);
			
			if (favoriteLink == null) {
				throw new RollbackException("Transaction : id="+id+" no longer exists");
			}
			
			switch transaction.getTransacType(){
				case 'B':
					
			}
				
			transaction.setClick(favoriteLink.getClick()+1);
			
			update(favoriteLink);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
