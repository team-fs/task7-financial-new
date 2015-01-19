package databeans;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fund_id, price_date")

public class PriceBean {
	int fund_id;
	Date price_date;
	long price;
	
	public int getFundId() {
		return fund_id;
	}
	public void setFundId(int fund_id) {
		this.fund_id = fund_id;
	}
	public Date getPriceDate() {
		return price_date;
	}
	public void setPriceDate(Date price_date) {
		this.price_date = price_date;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
}
