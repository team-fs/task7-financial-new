package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id, fund_id")
public class PositionBean {
	
	private int customer_id;
	private int fund_id;
	private long shares;

	public PositionBean(int customer_id, int fund_id, int shares) {
		this.customer_id = customer_id;
		this.fund_id = fund_id;
		this.shares = 0;
	}

	public int getCustomerId() 		{ return customer_id; }
	public int getFundId() 			{ return fund_id; }
	public long getShares() 		{ return shares; }

	public void setCustomerId(int n){ customer_id = n; }
	public void setFundId(int n) 	{ fund_id = n; }
	public void setShares(long l)	{ shares = l;}
}