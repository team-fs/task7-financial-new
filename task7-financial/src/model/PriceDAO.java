//Jiayi Zhu
//jiayiz
//08-600
//Dec 10, 2014

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.PriceBean;

public class PriceDAO extends GenericDAO<PriceBean> {
	public PriceDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(PriceBean.class, tableName, cp);
	}

	public void create(PriceBean price) throws RollbackException {
		PriceBean[] prices = match(MatchArg.and(MatchArg.equals("fund_id", price.getFundId()),
				MatchArg.equals("price_date", price.getPriceDate()))) ;
		if (prices.length>0) {
			return;
		}
		try {
			Transaction.begin();

			createAutoIncrement(price);

			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public PriceBean[] getPrice(int fund_id) throws RollbackException {

		PriceBean[] prices = match(MatchArg.equals("fund_id", fund_id));
		return prices;
	}

}
