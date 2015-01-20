package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.FundBean;

public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(FundBean.class, tableName, cp);
	}

	public void create(FundBean Fund) throws RollbackException {
		FundBean[] funds = match(MatchArg.and(
				MatchArg.equals("id", Fund.getId()),
				MatchArg.equals("name", Fund.getName())));
		if (funds.length > 0) {
			return;
		}
		try {
			Transaction.begin();

			create(Fund);

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public FundBean[] getFund(int id) throws RollbackException {

		FundBean[] funds = match(MatchArg.equals("id", id));
		return funds;
	}

}
