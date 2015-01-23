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
import formbeans.RequestCheckForm;

public class ReqChkAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory.getInstance(RequestCheckForm.class);
	private TransactionDAO transactionDAO;
	
	public ReqChkAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "requestCheck.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			CustomerBean customer = (CustomerBean) request.getSession(false).getAttribute("customer");
			RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			TransactionBean transaction = new TransactionBean();
			transaction.setCusId(customer.getCustomerId());
			transaction.setAmount(Long.parseLong(form.getAmount()));
			transaction.setTransacType('R');
			
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
