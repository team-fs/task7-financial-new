package databeans;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transaction_id")
public class TransactionBean {
	private int transaction_id;
    private int customer_id;
    private int fund_id;
    private Date execute_date;
    private long shares;
    private char transaction_type;
    private long amount;
    
    public int getTransacId () {
    	return transaction_id;
    }
    public int getCusId () {
    	return customer_id;
    }
    public int getFundId () {
    	return fund_id;
    }
    public Date getExeDate () {
    	return execute_date;
    }
    public double getShares () {
    	return shares;
    }
    public char getTransacType () {
    	return transaction_type;
    }
    public double getAmount () {
    	return amount;
    }
    
    public void setTransacId (int transacId) {
    	transaction_id = transacId;
    }
    public void setCusId (int customerId) {
    	customer_id = customerId;
    }
    public void setFundId (int fundId) {
    	fund_id = fundId;
    }
    public void setExeDate (Date executeDate) {
    	execute_date = executeDate;
    }
    public void setShares (long shares) {
    	this.shares = shares;
    }
    public void setTransacType (char transactionType) {
    	transaction_type = transactionType;
    }
    public void amount (long amount) {
    	this.amount= amount;
    }
}
