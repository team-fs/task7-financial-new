package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.PositionDAO;
import model.PriceDAO;
import model.TransactionDAO;
import model.Model;
import model.CustomerDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import formbeans. CustomerForm;

public class ViewByEmployeeAction extends Action{
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory.getInstance( CustomerForm.class);

	private TransactionDAO transacDAO;
	private CustomerDAO cusDAO;
	private PositionDAO posDAO;
	//private PriceDAO priceDAO;

	public ViewByEmployeeAction (Model model) {
		transacDAO = model.getTransactionDAO();
		cusDAO = model.getCustomerDAO();
        posDAO = model.getPositionDAO();
        //priceDAO = model.getPriceDAO();
	}

	public String getName() {
		return "viewByEmployee.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			//List the all the customer in the dropdown menu.
			request.setAttribute("customerList", cusDAO.getCustomer());
            CustomerForm form = formBeanFactory.create(request);
			
			errors.addAll(form.getValidationErrors());
			//Get certain customer
			String id = form.getCusId();
			int cusId =  Integer.parseInt(id);
			CustomerBean customer = cusDAO.read(cusId);
			TransactionBean[] transactions = transacDAO.getTransactions(cusId);
			PositionBean[] positions = posDAO.getPositions(cusId);
			//PriceBean[] price = priceDAO.getPrice(fund)
			
			request.setAttribute("customer", customer);
			request.setAttribute("transaction", transactions[transactions.length-1]); //Return the last trading day.
			request.setAttribute("positions", positions);
			request.setAttribute("customer", customer);
			//request.setAttribute("price", price);
			
			return "viewAccountByEmployee.jsp";
		} catch (NumberFormatException e) {
			errors.add("Invalid customer");
			return "error.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
