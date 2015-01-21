package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.TransactionBean;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;
import model.FundDAO;
import formbeans.BuyForm;

public class BuyFundAction extends Action {
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory.getInstance(BuyForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	
	public BuyFundAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "buyfund.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			CustomerBean customer = (CustomerBean) request.getSession(false).getAttribute("customer");
			BuyForm form  = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			
			TransactionBean transaction = new TransactionBean();
			transaction.setCusId(customer.getCustomerId());
			transaction.setFundId(0); //should obtain from fund table, which is not established so far. So recorded as 0 temporarily here.
			transaction.setAmount(form.getAmount());
			transaction.setTransacType('B');
			//加一个判断语句：amount<cash
			transactionDAO.create(transaction);
			
			return "index.jsp";
		} catch(RollbackException e) {
			errors.add(e.getMessage());
			return "index.jsp";
		} catch(FormBeanException e) {
			errors.add(e.getMessage());
			return "index.jsp";
		}
	}
	

}
