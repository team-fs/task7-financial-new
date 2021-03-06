package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.TransactionBean;
import model.Model;
import model.TransactionDAO;
import formbeans.SellForm;

public class SellFundAction extends Action {
	private FormBeanFactory<SellForm> formBeanFactory = FormBeanFactory.getInstance(SellForm.class);
	private TransactionDAO transactionDAO;
	
	public SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "sellfund.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			CustomerBean customer = (CustomerBean) request.getSession(false).getAttribute("customer");
			SellForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			TransactionBean transaction = new TransactionBean();
			transaction.setCusId(customer.getCustomerId());
			transaction.setFundId(0);
			transaction.setShares(form.getShareNumber());
			transaction.setTransacType('S');
			
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
