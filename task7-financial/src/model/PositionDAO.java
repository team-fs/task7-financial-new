package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.DuplicateKeyException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.PositionBean;
import databeans.CustomerBean;
import databeans.FundBean;

public class PositionDAO extends GenericDAO<PositionBean>{
	public PositionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(PositionBean.class, tableName, pool);
	}

	public PositionBean[] getPositions() throws RollbackException {
		PositionBean[] positions = match();
		return positions;
	}
	
	public void create(PositionBean newPosition) throws RollbackException {
		try {
			Transaction.begin();
			int curCus = newPosition.getCustomerId();
			int curFund = newPosition.getFundId();
						
			PositionBean[] DupPositions = match(MatchArg.equals("customerId",curCus), MatchArg.equals("fundId",curFund));
	        if(DupPositions!=null && DupPositions.length>0) {
	        	for(PositionBean pos:DupPositions){
	        		pos.setShares(pos.getShares() + newPosition.getShares());
					update(pos);
	        	}
	        }	
			create(newPosition);
			Transaction.commit();
		}		
		finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void delete(int customerId, int fundId) throws RollbackException {
		try {
			Transaction.begin();
    		PositionBean position = read(customerId, fundId);

    		if (position == null) {
				throw new RollbackException("Entry not found.");
    		}

			delete(customerId, fundId);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}